package ru.selenium.helperInterface;

import org.openqa.selenium.WebDriver;
import ru.selenium.helper.*;
import ru.selenium.util.BaseMethod;

public interface ApplicationManager {

    void stop();

    RegisterPageHelper2 getRegHelper();

    MainPageHelper2 getMainHelper();

    NavigationHelper2 getNavigateHelper();

    WebDriver getWebDriver();

    BaseMethod getBaseMethod();

    String getBaseUrl();

    AuthPageHelper2 getAuthPageHelper();

    OdnoclassnikiHelper2 getOdnoclassnikiHelper();

    MailRuHelper2 getMailRuHelper();

    VkPageHelper2 getVkPageHelper();

    PropertiesHelper2 getPropertiesHelper();


}
