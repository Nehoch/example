package ru.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ru.selenium.pages.Site.*;

import ru.selenium.util.BaseMethod;
import ru.selenium.util.GenerateRandomData;

public class PageManager {

    public BaseMethod baseMethod;
    public MainPage mainPage;
    public AnyPageBeforeLogin beforeLogin;
    public AnyPageAfterLogin afterLogin;
    public RegisterPage registerPage;
    public AuthPage authPage;
    public OdnoclassnikiPage odnoclassniki;
    public MailRuPage mailRuPage;
    public VkPage vkPage;
    public GenerateRandomData generateRandomData;

    private WebDriver driver;

    public PageManager(WebDriver driver) {
        this.driver = driver;
        mainPage = initElements(new MainPage(this));
        afterLogin = initElements(new AnyPageAfterLogin(this));
        beforeLogin = initElements(new AnyPageBeforeLogin(this));
        registerPage = initElements(new RegisterPage(this));
        baseMethod = new BaseMethod(driver);
        authPage = initElements(new AuthPage(this));
        odnoclassniki = initElements(new OdnoclassnikiPage(this));
        mailRuPage = initElements(new MailRuPage(this));
        vkPage = initElements(new VkPage(this));
        generateRandomData = new GenerateRandomData();
    }

    private <T extends Page> T initElements(T page) {

        PageFactory.initElements(
                new DisplayedElementLocatorFactory(driver, 10), page);
        PageFactory.initElements(new DisplayedElementLocatorFactory(
                (WebDriver) driver, 10), page);
        return page;
    }

    public BaseMethod getBaseMethod() {
        if (baseMethod == null) {
            baseMethod = new BaseMethod(driver);
        }
        return baseMethod;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

}
