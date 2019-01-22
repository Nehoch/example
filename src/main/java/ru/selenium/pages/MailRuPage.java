package ru.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailRuPage extends Page {

    /**
     * Логин
     */
    @FindBy(id = "mailbox:login")
    public WebElement loginField;
    /**
     * Пароль
     */
    @FindBy(id = "mailbox:password")
    public WebElement passField;
    /**
     * Кнопка авторизации
     */
    @FindBy(id = "mailbox:submit")
    private WebElement gotoMailButton;
    private String loginMail = "xxx";
    private String passMail = "xxx";
    private String urlMail = "https://mail.ru/";

    public MailRuPage(PageManager pages) {
        super(pages);
        // TODO Auto-generated constructor stub
    }

    public void inputLoginField() {
        baseMethod.input(loginField, loginMail);
    }

    public void inputPassField() {
        baseMethod.input(passField, passMail);
    }

    public void clickGotoMailButton() {
        baseMethod.click(gotoMailButton, true);
    }

    public void navigateToMailru() {
        driver.navigate().to(urlMail);
    }

}
