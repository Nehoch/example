package ru.selenium.tests.Site;

import com.esotericsoftware.yamlbeans.YamlException;
import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.selenium.model.User;
import ru.selenium.pages.Retry;
import ru.selenium.pages.TestBase;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

@Features("Авторизация")

public class AuthTests extends TestBase {

    private static String oldTab;
    private static List<User> listUser = new ArrayList<User>();
    final String key = "1";

    @BeforeMethod(groups = {"smoke", "regres", "all", "auth"})
    public void before() {
        try {
            oldTab = app.getWebDriver().getWindowHandle();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @BeforeClass(groups = {"smoke", "regres", "all", "auth"})
    public void beforeClass() throws FileNotFoundException, YamlException {
        try {
            listUser = readYaml();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @AfterMethod(groups = {"smoke", "regres", "all", "auth"})
    public void qiut() {
        try {
            ArrayList<String> newTab = new ArrayList<String>(app.getWebDriver()
                    .getWindowHandles());
            for (String tab : newTab) {
                if (!tab.equals(oldTab)) {
                    app.getWebDriver().switchTo().window(tab);
                    app.getWebDriver().close();
                }
            }
            app.getWebDriver().switchTo().window(oldTab);
            app.getWebDriver().manage().deleteAllCookies();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @Stories("Хедер")
    // С главной страницы
    // Валидные данные
    @Test(retryAnalyzer = Retry.class, groups = {"smoke", "regres", "all", "auth"})
    public void successLoginMainPage() throws Exception {

        app.getNavigateHelper().goToBaseUrl();
        app.getMainHelper().loginInHeader(listUser.get(0));

        Assert.assertTrue("SuccessLoginMainPage. Header. User is not authorized. Not found avatar user", app.getBaseMethod().isElementPresent(
                app.getRegHelper().pages.afterLogin.goToProfileFromAvatarB));
    }

    @Stories("Хедер")
    // С главной страницы
    // Соцсеть: одноклассники
    @Test(retryAnalyzer = Retry.class, groups = {"smoke", "regres", "all", "auth"})
    public void successLoginMainPageOdnoclassniki() throws Exception {
        app.getOdnoclassnikiHelper().activeOdno();
        app.getNavigateHelper().goToLogoutUrl();
        app.getMainHelper().pages.mainPage.clickOdnkButtonInHeader();
        Assert.assertTrue("SuccessLoginMainPageOdnoclassniki. Header. User is not authorized. Not found avatar user", app.getBaseMethod().isElementPresent(
                app.getRegHelper().pages.afterLogin.goToProfileFromAvatarB));
    }

    @Stories("Страница авторизации")
    // Со страницы авторизации
    // Валидные данные
    @Test(retryAnalyzer = Retry.class, groups = {"regres", "all", "auth"})
    public void successLoginAuthPage() throws Exception {
        app.getNavigateHelper().goToAuthUrl();
        app.getAuthPageHelper().successLogin(false, listUser.get(1));

        Assert.assertTrue("successLoginAuthPage. Page /user/auth/. User is not authorized. Not found avatar user", app.getBaseMethod().isElementPresent(
                app.getRegHelper().pages.afterLogin.goToProfileFromAvatarB));

    }

    @Stories("Страница авторизации")
    // Со страницы авторизации
    // Логин содержит спецсимволы
    @Test(retryAnalyzer = Retry.class, groups = {"regres", "all", "auth"})
    public void unsuccessLoginAuthPageLoginSpecSimbol() throws Exception {
        app.getNavigateHelper().goToAuthUrl();
        app.getAuthPageHelper().successLogin(
                new User().setLogin(ValidationSpaceAndSpecSimbol(10, false))
                        .setPassword(GetRandomFull(9)));
        assertThat(app.getAuthPageHelper().pages.authPage.getLoginFieldError(),
                equalToIgnoringCase("Логин не должен содержать спецсимволов!"));
    }
}
