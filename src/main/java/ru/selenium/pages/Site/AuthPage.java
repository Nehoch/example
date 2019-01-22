package ru.selenium.pages.Site;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.selenium.model.User;
import ru.selenium.pages.PageManager;
import ru.selenium.util.MyException;

public class AuthPage extends AnyPageBeforeLogin {

    /**
     * Крошки - ссылка на главную
     */
    @FindBy(xpath = "//*[@class='breadcrumbs']/a")
    public WebElement breadcrumbsGoToMainH;
    /**
     * В крошках - "авторизация"
     */
    @FindBy(xpath = "//*[@class='breadcrumbs']/span")
    public WebElement breadcrumbsAuthT;
    /**
     * Поле логин
     */
    @FindBy(id = "login")
    public WebElement loginField;
    /**
     * Ошибки в логине
     */
    @FindBy(xpath = "//*[@id='login_error']/span")
    public WebElement loginFieldError;
    /**
     * Поле пароль
     */
    @FindBy(id = "password")
    public WebElement passField;
    /**
     * Ошибки в пароле
     */
    @FindBy(xpath = "//*[@id='password_error']/span")
    public WebElement passFieldError;
    /**
     * нотис об ошибке
     */
    @FindBy(xpath = "//*[@class='content']//*[contains(@class,'notice notice')]")
    public WebElement authErrorNotice;
    /**
     * Кнопка логина
     */
    @FindBy(xpath = "//*[contains(@class,'button middle green')]")
    public WebElement loginInButton;
    /**
     * Зарегистрируйтесь
     */
    @FindBy(xpath = "//*[@class='content_wrapper']//*[@href='/registration']")
    public WebElement goToRegisterH;
    /**
     * Забыли пароль?
     */
    @FindBy(xpath = "//*[@class='content_wrapper']//*[@href='/user/pass_restore']")
    public WebElement goToRestoreH;
    /**
     * Восстановление пароля пришло успешно
     */
    @FindBy(xpath = "//*[@class='notice notice_content js-notice-auth-placeholder success']")
    public WebElement successRestore;
    /**
     * Поле для ввода капчи
     */
    @FindBy(id = "field_kcaptcha")
    public WebElement capchaField;
    /**
     * обновить капчу
     */
    @FindBy(xpath = "//*[@onclick='reload_kcaptcha()']")
    public WebElement capchaRefreshH;


    /**
     * Соц сети
     */
    /**
     * Вконтакте
     */
    @FindBy(xpath = "//*[@class='social_reg']//a[contains(@onclick,'VK')]/img")
    public WebElement vkButton;
    /**
     * Одноклассники
     */
    @FindBy(xpath = "//*[@class='social_reg']//a[contains(@onclick,'Odk')]/img")
    public WebElement odnoklassnikiButton;
    /**
     * Маилру
     */
    @FindBy(xpath = "//*[@class='social_reg']//a[contains(@onclick,'Mailru')]/img")
    public WebElement mailRuButton;

    public AuthPage(PageManager pages) {
        super(pages);
        PageFactory.initElements(driver, this);
    }

    public void inputLoginField(User user) {
        if (user.getLogin() == null) {
            user.setLogin("");
        }
        baseMethod.input(loginField, user.getLogin());
    }

    public void inputPassField(User user) {
        if (user.getPassword() == null) {
            user.setPassword("");
        }
        baseMethod.input(passField, user.getPassword());
    }

    public void inputCapchaField(User user) {
        if (user.getLogin() == null) {
            user.setLogin("");
        }
        baseMethod.input(capchaField, user.getCapcha());
    }

    public void clickLoginInButton() {
        baseMethod.click(loginInButton, true);
    }

    public void clickLoginInButton(boolean jsclick) {
        baseMethod.click(loginInButton, jsclick);
    }

    public void clickVkButton() throws MyException {
        String oldTab = driver.getWindowHandle();
        baseMethod.clickAndWait(vkButton);
        if (!baseMethod.isElementPresent(pages.afterLogin.goToProfileFromAvatarB, 3))
            throw new MyException("user no auth");
        driver.switchTo().window(oldTab);
    }

    public void clickOdnkButton() throws MyException {
        String oldTab = driver.getWindowHandle();
        baseMethod.clickAndWait(odnoklassnikiButton);
        if (!baseMethod.isElementPresent(pages.afterLogin.goToProfileFromAvatarB, 3))
            throw new MyException("user no auth");
        driver.switchTo().window(oldTab);
    }

    public void clickMailRuButton() throws MyException {
        String oldTab = driver.getWindowHandle();
        baseMethod.clickAndWait(mailRuButton);
        if (!baseMethod.isElementPresent(pages.afterLogin.goToProfileFromAvatarB, 3))
            throw new MyException("user no auth");
        driver.switchTo().window(oldTab);
    }

    public void clickGoToRegisterH() {
        baseMethod.click(goToRegisterH, false);
    }

    public void clickGoToRestoreH() {
        baseMethod.click(goToRestoreH, false);
    }

    public String getPassFieldError() throws Exception {
        return baseMethod.getText(passFieldError);
    }

    public String getLoginFieldError() throws Exception {
        return baseMethod.getText(loginFieldError);
    }

    public String getAuthErrorNotice() throws Exception {
        try {
            return baseMethod.getText(authErrorNotice);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            return baseMethod.getText(authErrorNotice);
        }
    }

    public boolean getVisibleButton() {
        boolean var = false;
        if (loginInButton.isDisplayed()) {
            var = false;
        }
        if (loginInButton.isEnabled()) {
            var = true;
        }
        return var;
    }
}
