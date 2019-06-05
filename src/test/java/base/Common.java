package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

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

        if (seleniumWebDriver == null) {
            System.setProperty("webdriver.chrome.driver", "src/chromedriver74-0-3729-6.exe");
            driver = new ChromeDriver();
        } else {
            switch (seleniumWebDriver) {
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver", "src/chromedriver74-0-3729-6.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addExtensions(new File("src/chropath/ChroPath503.crx"));
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
        }
        return driver;
    }
}
