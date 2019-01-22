package ru.selenium.pages.Site;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.selenium.model.User;
import ru.selenium.pages.Page;
import ru.selenium.pages.PageManager;

public class AnyPageBeforeLogin extends Page {
    /**
     * Лого
     */
    @FindBy(xpath = "//header//*[@href='/']")
    private WebElement logo;
    /**
     * Кнопка Зарегистрироваться
     */
    @FindBy(xpath = "//*[@class='login_wrapper']//*[text()='Регистрация']")
    private WebElement goToRegisterFormB;
    /**
     * Забыли пароль
     */
    @FindBy(xpath = "//*[@class='login_forget']")
    private WebElement goToRestorePassH;
    /**
     * Поле логин
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[@name='auth[login]']")
    private WebElement loginFieldInHeader;

    // /
    // / Логин форма
    // /
    /**
     * Поле пароль
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[@name='auth[password]']")
    private WebElement passFieldInHeader;
    /**
     * Кнопка Войти
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[@value='Войти']")
    private WebElement loginInButtonInHeader;


    // /
    // /Для соц сетей
    // /
    /**
     * Вконтакте
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[contains(@onclick, 'VK')]")
    private WebElement VkButtonInHeaderB;
    /**
     * Одноклассники
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[contains(@onclick, 'Odk')]")
    private WebElement odnoklassnikiButtonInHeaderB;
    /**
     * Мой мир (Маил Ру)
     */
    @FindBy(xpath = "//*[@id='js-auth-login-block']//*[contains(@onclick, 'Mail')]")
    private WebElement mailRuButtonInHeaderB;

    public AnyPageBeforeLogin(PageManager pages) {
        super(pages);
        PageFactory.initElements(driver, this);
    }

    public void inputLoginFieldInHeader(User user) {
        baseMethod.input(loginFieldInHeader, user.getLogin());
    }

    public void inputPassFieldInHeader(User user) {
        baseMethod.input(passFieldInHeader, user.getPassword());
    }

    public void clickLoginInButtonInHeader() {
        baseMethod.clickAndWait(loginInButtonInHeader);
    }

    public void clickVkButtonInHeader() {
        String oldTab = driver.getWindowHandle();
        baseMethod.click(VkButtonInHeaderB);
        driver.switchTo().window(oldTab);
    }

    public void clickOdnkButtonInHeader() {
        String oldTab = driver.getWindowHandle();
        baseMethod.click(odnoklassnikiButtonInHeaderB);
        driver.switchTo().window(oldTab);
    }

    public void clickMailRuButtonInHeader() {
        String oldTab = driver.getWindowHandle();
        baseMethod.click(mailRuButtonInHeaderB);
        driver.switchTo().window(oldTab);
    }

    public void clickGoToRestorePassH() {
        baseMethod.click(goToRestorePassH, false);
    }

    public void clickGoToRegisterFormB() {
        baseMethod.click(goToRegisterFormB, false);
    }



}
