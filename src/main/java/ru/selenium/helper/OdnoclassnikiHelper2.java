package ru.selenium.helper;

import org.openqa.selenium.By;

public class OdnoclassnikiHelper2 extends DriverBasedHelper {

    public OdnoclassnikiHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
    }

    public void activeOdno() {
        pages.odnoclassniki.navigateToOdno();
        if (pages.baseMethod.isElementPresent(pages.odnoclassniki.loginField)) {
            pages.odnoclassniki.inputLoginField();
            pages.odnoclassniki.inputPassField();
            pages.odnoclassniki.clickGotoOdnoButton();
        }
        pages.baseMethod.waitForElementPresent(By
                .xpath(".//*[@class='toolbar_decor']"));
    }

}
