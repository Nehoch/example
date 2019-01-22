package ru.selenium.pages;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.selenium.helper.ApplicationManager2;
import ru.selenium.helperInterface.ApplicationManager;
import ru.selenium.model.User;
import ru.selenium.util.GenerateRandomData;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

@Listeners(TestListener.class)
public abstract class TestBase extends GenerateRandomData {

    @Inject
    WebDriver driver;
    private static final Logger LOG = Logger.getLogger(TestBase.class);
    protected ApplicationManager app;
    protected ManagerCookies cookieManager;

    protected int i;
    protected Retry retry = new Retry();

    protected GenerateRandomData generator;

    @BeforeMethod(groups = {"smoke", "regres", "all", "auth", "profile", "reg", "restore"})
    public void init() {
        try {
            if (app == null) {
                app = new ApplicationManager2();
                cookieManager = new ManagerCookies(app.getWebDriver(),
                        app.getBaseUrl());


                generator = new GenerateRandomData();
            }
            driver = app.getWebDriver();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }

    }

    public WebDriver getDriver() {
        return driver;
    }


    @AfterClass(groups = {"smoke", "regres", "all", "auth", "profile", "reg", "restore"})
    public void stop() {
        try {
            app.stop();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setupBeforeSuite(ITestContext context) {
        try {
            context.setAttribute("WebDriver", app.getWebDriver());
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    @AfterMethod(groups = {"smoke", "regres", "all", "add", "everything"})
    public void onTestStart(ITestResult testResult) throws Exception {
        try {
            String nameTest = testResult.getInstanceName().substring(testResult.getInstanceName().lastIndexOf("."), testResult.getInstanceName().length()) + "_" + testResult.getName() + ".jpg";
            String nameFile = generateData() + nameTest;
            final String dir = System.getProperty("user.dir");
            String path = dir + "/" + generateDataFolder() + "/";
            if (testResult.getStatus() == ITestResult.FAILURE) {
                System.out.println("#######################################  " + new File(dir + "/" + nameFile).getAbsolutePath());
                File scrFile = ((TakesScreenshot) app.getWebDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(path + nameFile));
            }
            try {
                ITestNGMethod testNGMethod = testResult.getMethod();
                i = testNGMethod.getCurrentInvocationCount();

                if (i == retry.getMaxRetryCount() + 1) {
                    if (!testResult.isSuccess()) {
                        System.out.println("this build need failed");
                        String text = "null";
                        try {
                            text = testResult.getThrowable().getMessage().toString();
                        } catch (NullPointerException e) {
                            text = "null";
                        }
                        text = text.replaceFirst("\n", "");


                    }
                    i = 0;
                }
                if (testResult.isSuccess() || testResult.getStatus() == 3)
                    i = 0;
            } catch (Throwable e) {
                throw new Exception(e.getMessage(), e);
            }


            app.getWebDriver().manage().deleteAllCookies();
            app.getNavigateHelper().goToLogoutUrl();
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }

    public List<User> readYaml() throws FileNotFoundException, YamlException {
        try {
            System.out.println("#######################################  " + new File("src/test/resources/fixture.yml").getAbsolutePath());
            YamlReader reader = new YamlReader(new FileReader(new File("src/test/resources/fixture.yml")));
            List<User> listUser = new ArrayList<User>();
            while (true) {
                User user = reader.read(User.class);
                listUser.add(user);
                if (user == null) break;
            }
            return listUser;
        } catch (Exception e) {
            System.err.println("this build need failed");
            throw e;
        }
    }
}
