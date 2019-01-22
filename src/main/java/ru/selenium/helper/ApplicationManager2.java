package ru.selenium.helper;

import org.openqa.selenium.WebDriver;
import ru.selenium.helperInterface.ApplicationManager;
import ru.selenium.util.BaseMethod;
import ru.selenium.util.Browser;
import ru.selenium.util.PropertyLoader;
import ru.selenium.webdriver.WebDriverFactory;

public class ApplicationManager2 implements ApplicationManager {

    private String baseUrl;
    private RegisterPageHelper2 regHelper;
    private NavigationHelper2 navigateHelper;
    private BaseMethod baseMethod;
    private MainPageHelper2 mainHelper;
    private AuthPageHelper2 authPageHelper;
    private OdnoclassnikiHelper2 odnoclassnikiHelper;
    private MailRuHelper2 mailRuHelper;
    private VkPageHelper2 vkPageHelper;

    private WebDriver driver;
    private PropertiesHelper2 propertiesHelper;


    public ApplicationManager2() {
        baseUrl = PropertyLoader.loadProperty("site.url");
        String simbol = baseUrl.substring(baseUrl.length() - 1);
        if (simbol.equals("/"))
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

        Browser browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");

        driver = (WebDriverFactory.getInstance(gridHubUrl, browser,
                username, password));
        driver.manage().window().maximize();
    }

    @Override
    public void stop() {
        driver.close();
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
        }

    }

    public RegisterPageHelper2 getRegHelper() {
        if (regHelper == null) {
            regHelper = new RegisterPageHelper2(this);
        }
        return regHelper;
    }

    public NavigationHelper2 getNavigateHelper() {
        if (navigateHelper == null) {
            navigateHelper = new NavigationHelper2(this);
        }
        return navigateHelper;
    }

    public BaseMethod getBaseMethod() {
        if (baseMethod == null) {
            baseMethod = new BaseMethod(driver);
        }
        return baseMethod;
    }

    public MainPageHelper2 getMainHelper() {
        if (mainHelper == null) {
            mainHelper = new MainPageHelper2(this);
        }
        return mainHelper;
    }

    public AuthPageHelper2 getAuthPageHelper() {
        if (authPageHelper == null) {
            authPageHelper = new AuthPageHelper2(this);
        }
        return authPageHelper;
    }

    public OdnoclassnikiHelper2 getOdnoclassnikiHelper() {
        if (odnoclassnikiHelper == null) {
            odnoclassnikiHelper = new OdnoclassnikiHelper2(this);
        }
        return odnoclassnikiHelper;
    }

    public MailRuHelper2 getMailRuHelper() {
        if (mailRuHelper == null) {
            mailRuHelper = new MailRuHelper2(this);
        }
        return mailRuHelper;
    }

    public VkPageHelper2 getVkPageHelper() {
        if (vkPageHelper == null) {
            vkPageHelper = new VkPageHelper2(this);
        }
        return vkPageHelper;
    }

    public PropertiesHelper2 getPropertiesHelper() {
        if (propertiesHelper == null) {
            propertiesHelper = new PropertiesHelper2(this);
        }
        return propertiesHelper;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }


}
