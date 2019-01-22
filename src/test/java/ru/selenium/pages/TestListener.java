package ru.selenium.pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by PC on 01.06.2016.
 */
public class TestListener implements ITestListener {

    @Attachment(value = "{0}", type = "image/png")
    protected byte[] saveAllureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("***** Error " + result.getName() + " test has failed *****");
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        Object currentClass = result.getInstance();
        WebDriver webDriver = ((TestBase) currentClass).getDriver();
        saveAllureScreenshot(webDriver);
    }


    public void onTestStart(ITestResult result) {

    }


    public void onTestSuccess(ITestResult result) {

    }


    public void onTestSkipped(ITestResult result) {

    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


    public void onStart(ITestContext context) {

    }


    public void onFinish(ITestContext context) {

    }
}
