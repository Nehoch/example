package ru.selenium.helper;


import org.openqa.selenium.By;

public class VkPageHelper2 extends DriverBasedHelper {

    public VkPageHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
    }

    public void activeVk() {
        pages.vkPage.navigateToVk();
        if (pages.baseMethod.isElementPresent(pages.vkPage.loginField)) {
            pages.vkPage.inputLoginField();
            pages.vkPage.inputPassField();
            pages.vkPage.clickGotoVkButton();
        }
        pages.baseMethod
                .waitForElementPresent(By.xpath("//*[@class='left_label inl_bl' and text()='Моя Страница']"));
    }

}
