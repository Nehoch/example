package ru.selenium.helper;

import org.openqa.selenium.By;

public class MailRuHelper2 extends DriverBasedHelper  {

    public MailRuHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
        // TODO Auto-generated constructor stub
    }

    public void activeMailru() {
        pages.mailRuPage.navigateToMailru();
        if (pages.baseMethod.isElementPresent(pages.mailRuPage.loginField)) {
            pages.mailRuPage.inputLoginField();
            pages.mailRuPage.inputPassField();
            pages.mailRuPage.clickGotoMailButton();
        }
        if (!pages.baseMethod.isElementPresent(pages.mailRuPage.loginField) && !driver.getCurrentUrl().contains("e.mail.ru")) {
            driver.navigate().to("https://e.mail.ru/messages/inbox/");
        }
        pages.baseMethod
                .waitForElementPresent(By
                        .xpath("//*[@class='b-toolbar__btn__text b-toolbar__btn__text_pad']"));

    }

}
