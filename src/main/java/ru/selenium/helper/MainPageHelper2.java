package ru.selenium.helper;

import org.apache.log4j.Logger;
import ru.selenium.helperInterface.MainPageHelper;
import ru.selenium.model.User;

public class MainPageHelper2 extends DriverBasedHelper implements
        MainPageHelper {
    private static final Logger log = Logger.getLogger(MainPageHelper2.class);
    public MainPageHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());

    }

    public void fillFormInHeader(User user) {
        pages.mainPage.inputLoginFieldInHeader(user);
        pages.mainPage.inputPassFieldInHeader(user);
    }

    public void loginInHeader(User user) {
        pages.baseMethod.waitForAjax();
        if (user.getPassword() == null) {
            user.setPassword("");
        }
        if (user.getLogin() == null) {
            user.setLogin("");
        }
        fillFormInHeader(user);
        log.info("Accaunt user: login is " + user.getLogin() + " , password - " + user.getPassword());
        pages.mainPage.clickLoginInButtonInHeader();

    }




}
