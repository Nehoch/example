package ru.selenium.helper;

import ru.selenium.helperInterface.PropertiesHelper;
import ru.selenium.util.PropertyLoader;

/**
 * Created by PC on 21.09.2015.
 */
public class PropertiesHelper2 extends DriverBasedHelper implements PropertiesHelper {

    public PropertiesHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
    }
}



