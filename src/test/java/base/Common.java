package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class Common {
    /**
     * print finding element in console
     *
     * @param nameElem finding element name
     */
    public static void outputFindingElement(String nameElem) {
        System.out.println("Finding element: " + nameElem);
    }

    /**
     * driver selection
     *
     * @param seleniumWebDriver required driver
     * @return driver for work
     */
    public static WebDriver selectDriver(String seleniumWebDriver) {
        WebDriver driver;

        if (seleniumWebDriver == null) seleniumWebDriver = "CHROME";
        switch (seleniumWebDriver) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", "src/chromedriver74-0-3729-6.exe");
                ChromeOptions options = new ChromeOptions();    //TODO вынести отдельно
                options.addExtensions(new File("src/chropath/ChroPath503.crx"));
                options.addArguments("start-maximized");
                options.addArguments("--auto-open-devtools-for-tabs");
                driver = new ChromeDriver(options);
                break;
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", "src/geckodriver0240.exe");
                driver = new FirefoxDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", "src/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/chromedriver74-0-3729-6.exe");
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    /**
     * Проскроллить страницу с заданным смещением от небходимого элемента
     *
     * @param webElement webelement
     * @param x          offset by x
     * @param y          offset ba y
     */
    public static void scrollPageWithOffset(WebDriver driver, WebElement webElement, int x, int y) {

        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor) driver).executeScript(code, webElement, x, y);
    }

    /**
     * Проскроллить страницу до небходимого элемента
     *
     * @param driver     driver
     * @param webElement путь до требуемого элемента
     */
    public static void scrollPageToWebElement(WebDriver driver, WebElement webElement) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , webElement);
    }

    /**
     * Проскроллить страницу до небходимого элемента, используя Xpath
     *
     * @param driver  driver
     * @param element элемент, к которому нужно доскролить
     */
    public static void scrollPageToWebElementByXpath(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        //((JavascriptExecutor) driver).executeAsyncScript("window.scrollBy(0,-100)");
    }

    /**
     * Проскроллить страницу до небходимого элемента, используя Xpath
     *
     * @param driver driver
     * @param xpath  путь до требуемого элемента
     */
    public static void scrollPageToWebElementByXpath(WebDriver driver, String xpath) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , webElement);
    }

    public static void scrollPageWithOffsetByXpath(WebDriver driver, String xpath, int x, int y) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor) driver).executeScript(code, webElement, x, y);
    }

    public static void assertThatElementDisplayed(WebDriver driver, By locator) {

        assertThat(driver.findElement(locator).isDisplayed()).isTrue();
    }

    public static void assertThatElementDisplayed(WebDriver driver, WebElement webElement) {

        assertThat(webElement.isDisplayed()).isTrue();
    }

    public static boolean elementClickable(WebDriver driver, WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * метод для ожидания полной загрущки AJAX
     * @param driver driver
     * @return loaded AJAX or not in wait time
     */
    public static boolean waitForJSandJQueryToLoad(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
