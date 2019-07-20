package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static locators.YMSubCategoryManufacturePageLocators.TITLE;
import static locators.YMSubCategoryManufactureQuantityPageLocators.QUANTITY_COUNT;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YMSubCategoryManufactureQuantityPage {
    private WebDriver driver;

    YMSubCategoryManufactureQuantityPage(WebDriver driver) {
        this.driver = driver;
    }

    public Integer getQuantityGoods(int quantity) throws InterruptedException {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(visibilityOfElementLocated(By.xpath("//button/span[contains(text(),'Показывать по " + quantity + "')]"))); //TODO не подходит. Нужно дождаться полной загрузки всех элементов
        return driver.findElements(By.xpath(QUANTITY_COUNT.toString())).size();

    }
}
