package ru.selenium.tests.Site;

import com.esotericsoftware.yamlbeans.YamlException;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.selenium.model.User;
import ru.selenium.pages.Retry;
import ru.selenium.pages.TestBase;
import ru.selenium.util.PropertyLoader;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
@Features("Регистрация с формы регистрации")
public class RegTests extends TestBase {

    private static List<User> listUser = new ArrayList<User>();

    final String key = "2";

    @BeforeMethod(groups = {"smoke", "regres", "all", "reg"})
    public void before() {
        try {
            app.getNavigateHelper().goToRegisterUrl();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @BeforeClass(groups = {"smoke", "regres", "all", "reg"})
    public void beforeClass() throws FileNotFoundException, YamlException {
        try {
            listUser = readYaml();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }
    @Stories("Пустая форма")
    // С формы регистрации
    // Пустая форма
    @Test(retryAnalyzer = Retry.class, groups = {"regres", "all", "reg"})
    public void unvalidRegformNullAll() throws Exception {

        Assert.assertTrue("UnsuccessRegTests. Page /registration. Button in not visible",app.getRegHelper().pages.registerPage
                .getVisibleButton());
        cookieManager.setCookie(PropertyLoader.loadProperty("cookiename"), PropertyLoader.loadProperty("cookievalue"));
    }

    @Stories("На почтовый ящик")
    // С формы регистрации
    // На почтовый ящик
    // Логин минимальной длины (4 символа)
    @Test(retryAnalyzer = Retry.class, groups = {"smoke", "regres", "all", "reg"})
    public void validRegformEmailMinlogin() throws Exception {
        app.getRegHelper().fillFormReg(
                false,
                new User().setLogin(GetRandomFull(3) + key)
                        .setPassword(GetRandomFull(11)).setAgreement(true)
                        .setCheckEmail(true).setEmail(GetEmail()));
        Assert.assertTrue("SuccessRegTests. Page /registration. User is not authorized. Not found avatar user",app.getBaseMethod().isElementPresent(
                app.getRegHelper().pages.afterLogin.goToProfileFromAvatarB));

    }


    @Stories("На почтовый ящик")
    // С формы регистрации
    // Регистрация на почтовый ящик gmail, отличающийся от уже ранее созданного
    // только наличием точки в имя ящика
    @Test(retryAnalyzer = Retry.class, groups = {"regres", "all", "reg"})
    public void unvalidRegformDoubleEmailWithPointGmailcom() throws Exception {
        User user = listUser.get(4);
        user.setEmail(user.getEmail().substring(0, 3) + "."
                + user.getEmail().substring(3, user.getEmail().length()));
        app.getRegHelper().fillFormReg(true, user);
        assertThat(
                app.getRegHelper().pages.registerPage.getTextEmailFieldError(),
                equalToIgnoringCase("Данный E-mail уже существует!"));
    }

}
