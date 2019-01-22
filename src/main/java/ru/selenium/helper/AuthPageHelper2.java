package ru.selenium.helper;

import ru.selenium.helperInterface.AuthPageHelper;
import ru.selenium.model.User;

public class AuthPageHelper2 extends DriverBasedHelper implements
        AuthPageHelper {

    public AuthPageHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
    }

    public void fillForm(User user) {
        pages.authPage.inputLoginField(user);
        pages.authPage.inputPassField(user);
    }

    public void successLogin(User user) {
        fillForm(user);
        pages.authPage.clickLoginInButton();
    }

    public void successLogin(boolean var, User user) {
        fillForm(user);
        pages.authPage.clickLoginInButton(var);
    }


}
