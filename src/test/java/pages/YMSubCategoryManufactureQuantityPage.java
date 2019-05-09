package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static locators.YMSubCategoryManufactureQuantityPageLocators.QUANTITY_COUNT;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YMSubCategoryManufactureQuantityPage {
    private WebDriver driver;

    YMSubCategoryManufactureQuantityPage(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getQuantityGoods() {

        return driver.findElements(By.xpath(QUANTITY_COUNT.toString())).size();

    }
}
