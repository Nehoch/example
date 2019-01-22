package ru.selenium.helper;

import ru.selenium.helperInterface.RegisterPageHelper;
import ru.selenium.model.User;

public class RegisterPageHelper2 extends DriverBasedHelper implements
        RegisterPageHelper {

    public RegisterPageHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
    }

    public void fillFormReg(boolean jsclick, User user) {
        pages.registerPage.inputEmailorPhone(user);
        pages.registerPage.inputLoginField(user);
        pages.registerPage.inputPassField(user);
        pages.registerPage.checkAgreeCheck(user);
        pages.registerPage.clickRegisterButton(jsclick);
        pages.baseMethod.waitForAjax();
    }

}
