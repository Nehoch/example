package ru.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.selenium.model.User;

public class OdnoclassnikiPage extends Page {

    /**
     * Поле логин
     */
    @FindBy(xpath = "//*[@id='field_email']")
    public WebElement loginField;
    /**
     * Поле Пароль
     */
    @FindBy(xpath = "//*[@id='field_password']")
    public WebElement passField;
    /**
     * Кнопка входа в почту
     */
    @FindBy(xpath = ".//*[@class='button-pro __wide']")
    public WebElement gotoMailButton;
    private String loginOdnoOne = "xxx";
    private String loginOdnoTwo = "xxx";
    private String passOdnoOne = "xxx";
    private String passOdnoTwo = "xxx";
    private String urlOdno = "http://www.odnoklassniki.ru/";

    public OdnoclassnikiPage(PageManager pages) {
        super(pages);
        // TODO Auto-generated constructor stub
    }

    public void inputLoginField(User user) {
        if (user.getLogin() == null) {
            user.setLogin("");
        }
        baseMethod.input(loginField, user.getLogin());
    }

    public void inputLoginField() {
        baseMethod.input(loginField, loginOdnoOne);
    }

    public void inputPassField() {
        baseMethod.input(passField, passOdnoOne);
    }

    public void clickGotoOdnoButton() {
        baseMethod.click(gotoMailButton);
    }

    public void navigateToOdno() {
        driver.navigate().to(urlOdno);
    }

}
