package ru.selenium.helper;

import ru.selenium.helperInterface.NavigationHelper;


public class NavigationHelper2 extends DriverBasedHelper implements
        NavigationHelper {

    private String baseUrl;

    public NavigationHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
        this.baseUrl = manager.getBaseUrl();
    }

    public void openMainPage() {
        pages.baseMethod.waitForAjax();
        driver.get(baseUrl);
    }

    public String getMeUrl() {
        return baseUrl;
    }

    public void navigateGotoUrl(String text) {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(text);
        pages.baseMethod.waitForAjax();
    }

    public void goToBaseUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl);
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }

    public void goToLogoutUrl() {
        pages.baseMethod.waitForAjax();
        driver.get(baseUrl + "/actions/user/logout");
        pages.baseMethod.checkAlert();
    }

    public void goToConfirmUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl);
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }

    public void goToRestoreUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl + "/user/pass_restore");
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }

    public void goToRegisterUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl + "/registration");
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }

    public void goToAuthUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl + "/user/auth/");
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }

    public void goToProfileUrl() {
        pages.baseMethod.waitForAjax();
        driver.navigate().to(baseUrl + "/profile");
        pages.baseMethod.checkAlert();
        pages.baseMethod.waitForAjax();
    }
}
