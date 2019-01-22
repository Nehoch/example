package ru.selenium.helperInterface;

public interface NavigationHelper {

     String getMeUrl();

     void navigateGotoUrl(String text);

     void goToBaseUrl();

     void goToLogoutUrl();

     void goToConfirmUrl();

     void goToRestoreUrl();

     void goToRegisterUrl();

     void goToAuthUrl();

     void goToProfileUrl();

    void openMainPage();

}
