package ru.selenium.pages.Site;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.selenium.model.User;
import ru.selenium.pages.PageManager;
import ru.selenium.util.MyException;

public class RegisterPage extends AnyPageBeforeLogin {

    /**
     * Крошки - ссылка на главную
     */
    @FindBy(xpath = "//*[@class='breadcrumbs']/a")
    public WebElement breadcrumbsGoToMainH;
    /**
     * Чек бокс согласия с правилами
     */
    @FindBy(xpath = "//*[contains(text(),'Мой возраст')]")
    public WebElement agreeRulesCheck;
    /**
     * В крошках - "регистрация"
     */
    @FindBy(xpath = "//*[@class='breadcrumbs']/span")
    private WebElement breadcrumbsRegisterT;

    /**
     * Поле логин
     */
    @FindBy(id = "fakelogin")
    private WebElement loginField;

    /**
     * Ошибки по логину
     */
    @FindBy(xpath = "//*[@id='fakelogin_error']/span")
    private WebElement loginFieldErrors;

    /**
     * Поле пароль
     */
    @FindBy(id = "password")
    private WebElement passField;

    /**
     * Ошибки по паролю
     */
    @FindBy(xpath = "//*[@id='password_error']/span")
    private WebElement passFieldErrors;

    /**
     * Поле емаил
     */
    @FindBy(id = "email")
    private WebElement emailField;

    /**
     * Ошибки по емаил
     */
    @FindBy(xpath = "//*[@id='email_error']/span")
    private WebElement emailFieldErrors;

    /**
     * Поле телефон
     */
    @FindBy(id = "mobile")
    private WebElement phoneField;

    /**
     * Ошибки по телефону
     */
    @FindBy(xpath = "//*[@id='mobile_error']/span")
    private WebElement phoneFieldErrors;

    /**
     * Радиобаттон Емаил
     */
    @FindBy(xpath = "//*[text()='E-mail']")
    private WebElement emailRadio;
    /**
     * Радиобаттон Телефон
     */
    @FindBy(xpath = "//*[text()='Телефон']")
    private WebElement phoneRadio;

    /**
     * Кнопка Регистрация
     */
    @FindBy(xpath = "//*[@value='Зарегистрироваться']")
    private WebElement registerButton;

    /**
     * Выпадающий список с кодами
     */
    @FindBy(xpath = "//*[@class='jq-selectbox__select']/div[contains(@class,'select-text')]")
    private WebElement lisfOfCodeActivisation;

    /**
     * +7
     */
    @FindBy(xpath = "//*[@data-jqfs-class='rus']")
    private WebElement phoneCodeRus;

    /**
     * +37
     */
    @FindBy(xpath = "//*[@data-jqfs-class='brus']")
    private WebElement phoneCodeBrus;

    /**
     * +38
     */
    @FindBy(xpath = "//*[@data-jqfs-class='ukr']")
    private WebElement phoneCodeUkr;


    /**
     * День
     */
    @FindBy(xpath = "//*[text()='День']")
    private WebElement timeLimitDay;

    /**
     * Неделя
     */
    @FindBy(xpath = "//*[text()='Неделя']")
    private WebElement timeLimitWeek;

    /**
     * Месяц
     */
    @FindBy(xpath = "//*[text()='Месяц']")
    private WebElement timeLimitMonth;

    /**
     * Ошибка, если нет чекбокса согласия с правилами
     */
    @FindBy(xpath = ".//*[@id='agree_error']/span")
    private WebElement agreeRulesError;
    /**
     * ссылка на правила
     */
    @FindBy(xpath = "//*[@id='agree']/../span/a")
    private WebElement rulesH;


    // /
    // / Для соц сетей
    // /
    /**
     * ВК
     */
    @FindBy(xpath = "//*[@class='social_reg']//*[contains(@onclick, 'VK')]")
    private WebElement vkButton;
    /**
     * Одноклассники
     */
    @FindBy(xpath = "//*[@class='social_reg']//*[contains(@onclick, 'Odkl')]")
    private WebElement odnoklassnikiButton;
    /**
     * Мой мир (маил)
     */
    @FindBy(xpath = "//*[@class='social_reg']//*[contains(@onclick, 'Mailru')]")
    private WebElement mailRuButton;


    public RegisterPage(PageManager pages) {
        super(pages);
        PageFactory.initElements(driver, this);
    }

    public void inputLoginField(User user) {
        if (user.getLogin() == null) {
            user.setLogin("sdfsdfsdf");
            baseMethod.input(loginField, user.getLogin());
            loginField.clear();
        } else
            baseMethod.input(loginField, user.getLogin());
    }

    public void inputPassField(User user) {
        if (user.getPassword() == null) {
            user.setPassword("dfgdfgfg");
            baseMethod.input(passField, user.getPassword());
            passField.clear();
        } else
            baseMethod.input(passField, user.getPassword());
        baseMethod.click(loginField);
    }


    public void inputEmailField(User user) {
        if (user.getEmail() == null) {
            user.setEmail("dfgdfg");
            baseMethod.input(emailField, user.getEmail());
            emailField.clear();
        } else {
            pages.baseMethod.waitForAjax();
            baseMethod.input(emailField, user.getEmail());
        }
    }

    public void inputPhoneField(User user) {
        if (user.getNumberPhone() == null) {
            user.setNumberPhone("34535");
            baseMethod.input(phoneField, user.getNumberPhone());
            phoneField.clear();
        } else
            baseMethod.input(phoneField, user.getNumberPhone());

    }

    public String getTextEmailFieldError() {
        return emailFieldErrors.getText();
    }

    public void clickRegisterButton(boolean var) {
        baseMethod.clickAndWait(registerButton, var);
    }

    public void clickRegisterButton() {
        baseMethod.clickAndWait(registerButton, true);
    }

    public void clickAgreeRules() {
        baseMethod.click(agreeRulesCheck);
    }

    public void selectionPhoneCode(User user) {
        baseMethod.click(lisfOfCodeActivisation);
        if (user.getPhoneCode() == null) {
            user.setPhoneCode("");
        }
        switch (user.getPhoneCode()) {
            case "+7":
            case "7":
                baseMethod.clickAndWait(phoneCodeRus);
                break;
            case "+37":
            case "37":
                baseMethod.clickAndWait(phoneCodeBrus);
                break;
            case "+38":
            case "38":
                baseMethod.clickAndWait(phoneCodeUkr);
                break;

            default:
                baseMethod.clickAndWait(phoneCodeRus);
                break;
        }
    }

    // Проставление чекбокса согласие с соглашением
    public void checkAgreeCheck(User user) {
        if (!user.getAgreement()) {
            // Снять чекбокс
            if (baseMethod.runScript("return $('#agree').prop('checked')", true).equals("success")) {
                clickAgreeRules();
            }
        }
        // поставить чекбокс
        if (user.getAgreement()) {
            if (!baseMethod.runScript("return $('#agree').prop('checked')", true).equals("success")) {
                clickAgreeRules();
            }
        }
    }

    public void inputEmailorPhone(User user) {
        if (user.getCheckEmail()) {
            if (!baseMethod.runScript("return $('#email_radio').prop('checked')").equals("true")) {
                baseMethod.click(emailRadio, false);
            }
            inputEmailField(user);
        }
        if (!user.getCheckEmail()) {
            if (baseMethod.runScript("return $('#email_radio').prop('checked')").equals("true")) {
                baseMethod.click(phoneRadio, false);
            }
            selectionPhoneCode(user);
            inputPhoneField(user);
        }
    }


    public boolean getVisibleButton() {
        boolean var = false;
        if (registerButton.isDisplayed()) {
            var = false;
        }
        if (registerButton.isEnabled()) {
            var = true;
        }
        return var;
    }
}
