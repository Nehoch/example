package ru.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VkPage extends Page {

    /**
     * Поле логин
     */
    @FindBy(xpath = "//*[@id='index_email']")
    public WebElement loginField;
    /**
     * Поле Пароль
     */
    @FindBy(xpath = "//*[@id='index_pass']")
    public WebElement passField;
    /**
     * Кнопка логина
     */
    @FindBy(xpath = "//*[@id='index_login_button']")
    public WebElement gotoVkButton;
    private String loginVk = "xxx";
    private String passVk = "xxxx";
    private String urlVk = "http://vk.com";

    public VkPage(PageManager pages) {
        super(pages);
    }

    public void inputLoginField() {
        baseMethod.input(loginField, loginVk);
    }

    public void inputPassField() {
        baseMethod.input(passField, passVk);
    }

    public void clickGotoVkButton() {
        baseMethod.click(gotoVkButton);
    }

    public void navigateToVk() {
        driver.navigate().to(urlVk);
    }

}
