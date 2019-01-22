package ru.selenium.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.selenium.pages.PageManager;

import java.util.ArrayList;
import java.util.List;

public class BaseMethod {
    private static final Logger log = Logger.getLogger(BaseMethod.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PageManager pages;

    /*
     * Constructor injecting the WebDriver interface
     *
     * @param webDriver
     */
    public BaseMethod(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 1);
    }

    public boolean isElementPresent(WebElement element) {
        waitForAjax();

        try {
            element.isEnabled();

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean isElementPresent(WebElement element, int time) {
        waitForAjax();

        boolean status = false;
        int i = 0;
        while (true) {
            if (i == 3) {
                status = false;
                break;
            }

            try {
                element.isEnabled();

                status = true;
                break;
            } catch (Exception e) {
                waitForAjax();
                i++;
            }
        }

        return status;

    }

    public boolean waitForAjax() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean jQcondition = false;

        try {
            if (!driver.getCurrentUrl().contains("e.mail.ru")) {
                new WebDriverWait(driver, 180) {
                }.until(new ExpectedCondition<Boolean>() {

                    @Override
                    public Boolean apply(WebDriver driverObject) {
                        return (Boolean) ((JavascriptExecutor) driverObject)
                                .executeScript("return jQuery.active == 0");
                    }
                });
                jQcondition = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return window.jQuery != undefined && jQuery.active === 0") && (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                return jQcondition;
            } else
                return true;
        } catch (Exception e) {
            return jQcondition;
        }

    }

    public void waitForElementPresent(final By locator) {
        (new WebDriverWait(driver, 20))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver arg0) {
                        return arg0.findElement(locator);
                    }
                });
    }

    public void waitForElementPresent(final WebElement element) {
        (new WebDriverWait(driver, 1))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver arg0) {
                        return element;
                    }
                });
    }

    public void checkAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (alert != null)
                alert.accept();
        } catch (Exception e) {
            //exception handling
        }
    }

    public void input(By locator, String sendText) {
        waitForAjax();
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                driver.findElement(locator).clear();
                if (!sendText.equals(""))
                    driver.findElement(locator).sendKeys(sendText);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public void input(WebElement element, String sendText) {
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                element.clear();
                if (!sendText.equals(""))
                    element.sendKeys(sendText);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public void input(By locator, String sendText, Boolean swich) {
        waitForAjax();
        if (swich) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", driver.findElement(locator));

        }
        input(locator, sendText);
    }

    public void input(WebElement element, String sendText, Boolean swich) {
        waitForAjax();
        if (swich) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", element);
        }
        input(element, sendText);
    }

    public void click(By locator, Boolean useJQuery) {
        JavascriptExecutor Js = (JavascriptExecutor) this;
        if (useJQuery) {
            Js.executeScript("arguments[0].click();",
                    driver.findElement(locator));
        } else retryingFindClick(locator);

    }

    public void click(WebElement element, Boolean useJQuery) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        if (useJQuery) {
            js.executeScript("arguments[0].click();", element);
        } else
            retryingFindClick(element);
    }

    public void clickAndWait(WebElement element, Boolean useJQuery) {
        waitForAjax();
        click(element, useJQuery);
        waitForAjax();
    }

    public void addNewWindow(String var) {
        waitForAjax();
        ((JavascriptExecutor) driver)
                .executeScript(("window.open('" + var + "','_blank');"));
    }

    public void click(By locator) {
        waitForAjax();
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                retryingFindClick(locator);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForAjax();
    }

    public void click(WebElement element) {

        waitForAjax();
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                retryingFindClick(element);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                } else {
                    throw e;
                }
            }
            if (breakIt) {
                break;
            }
        }

    }


    public void clickAndWait(WebElement element) {
        waitForAjax();
        click(element);
        waitForAjax();
    }

    public void retryingFindClick(WebElement element) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }


    public void retryingFindClick(By by) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                driver.findElement(by).click();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public Boolean isElementPresent(By element) {
        waitForAjax();
        boolean isPresent = true;
        try {
            if (driver.findElement(element).isEnabled()) {

            }
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }

    public void click(By locator, Boolean useJQuery, Boolean swich) {
        waitForAjax();
        JavascriptExecutor Js = (JavascriptExecutor) this;
        if (useJQuery) {
            Js.executeScript("arguments[0].click();",
                    driver.findElement(locator));
        } else {
            if (swich) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", driver.findElement(locator));
            }
            retryingFindClick(locator);
        }
        waitForAjax();
    }

    public void click(WebElement element, Boolean useJQuery, Boolean swich) {
        waitForAjax();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        if (useJQuery) {
            js.executeScript("arguments[0].click();", element);
        } else {
            if (swich) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", element);
            }
            retryingFindClick(element);
        }
        waitForAjax();
    }

    public Boolean isTextPresent(String text) {
        waitForAjax();
        boolean isPresent = true;
        try {
            driver.findElement(By.xpath(".//*[contains(text(),'" + text + "')]"));
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }


    public Boolean isElementPresentAndWait(WebElement element, int timeout) {
        waitForAjax();
        Boolean isPresent;
        try {
            waitForAjax();
            if (element.isEnabled()) {

            }
            isPresent = true;
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }

    public String getText(WebElement element) throws Exception {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitForAjax();

                return element.getText();
            } catch (StaleElementReferenceException elementHasDisappeared) {

            }
            attempts++;
        }
        throw new Exception("not fount element" + element);
    }

    public void mouseOver(WebElement element) {
        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );"
                + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor) driver).executeScript(code, element);
    }

    private boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    public String getAttribute(WebElement element, String text) throws Exception {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitForAjax();
                return element.getAttribute(text);
            } catch (StaleElementReferenceException elementHasDisappeared) {

            }
            attempts++;
        }
        throw new Exception("not fount element" + element);
    }

    public String runScript(String script) {
        return (String) (((JavascriptExecutor) driver).executeScript(script)).toString();
    }

    public String runScript(String script, boolean contains) {
        if (contains) {
            try {
                return runScript(script);
            } catch (NullPointerException nul) {
                return "null";
            }
        } else
            return runScript(script);

    }

    public WebElement getElementFromTable(WebElement table, int roww, int column) {
        waitForAjax();
// Now get all the TR elements from the table
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
// And iterate over them, getting the cells
        List<WebElement> cells = allRows.get(roww - 1).findElements(By.tagName("td"));
        return cells.get(column - 1);
    }

    public String openAndSwitchToNewTab(String url) {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        String script = "window.open('" + url + "','_blank');";
        ((JavascriptExecutor) driver).executeScript(script);
        ArrayList<String> newTabFull = new ArrayList<String>(driver.getWindowHandles());
        newTabFull.removeAll(newTab);
        waitForAjax();
        driver.switchTo().window(newTabFull.get(0));
        waitForAjax();
        return newTabFull.get(0);


    }
}
