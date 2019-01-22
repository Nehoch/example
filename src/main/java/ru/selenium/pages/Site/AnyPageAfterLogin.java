package ru.selenium.pages.Site;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.selenium.pages.Page;
import ru.selenium.pages.PageManager;

import java.math.BigDecimal;

public class AnyPageAfterLogin extends Page {

    /**
     * Перейти в настройки профиля - нажав на аватар
     */
    @FindBy(xpath = "//*[@class='avatar_wrapper']/a")
    public WebElement goToProfileFromAvatarB;


    public AnyPageAfterLogin(PageManager pages) {
        super(pages);
        PageFactory.initElements(driver, this);
    }









}
