package ru.selenium.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManagerCookies {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    /*
     * Constructor injecting the WebDriver interface
     *
     * @param webDriver
     */
    public ManagerCookies(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        wait = new WebDriverWait(driver, 10);
    }

    public void setCookie(String cookie, String valuy) {
        if (cookie != null && cookie != "") {
            try {

                driver.manage().deleteCookieNamed(cookie);
                driver.manage().addCookie(
                        new Cookie(cookie, valuy, baseUrl.replace("http://",
                                "").replace("/", ""), "/", null));


            } catch (Exception e) {
                // TODO: handle exception
            }
            driver.navigate().refresh();
        }

    }


}
