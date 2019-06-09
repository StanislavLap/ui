package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static base.Common.*;
import static java.lang.Thread.sleep;
import static locators.YMSubCategoryManufacturePageLocators.QUANTITY_ITEMS;
import static locators.YMSubCategoryManufacturePageLocators.TITLE;
import static locators.YMSubCategoryManufactureSortingPricePageLocators.FROM;
import static locators.YMSubCategoryManufactureSortingPricePageLocators.TO;
import static locators.YMSubCategoryManufactureViewPage.LIST;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YMSubCategoryManufacturePage {
    private WebDriver driver;
    private WebDriverWait wait;


    YMSubCategoryManufacturePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        wait = new WebDriverWait(driver, 40);
        wait.until(visibilityOfElementLocated(By.xpath(TITLE.toString())));
        return driver.findElement(By.tagName("h1")).getText();
    }

    public YMSubCategoryManufactureQuantityPage selectQuantity(int quantity) {

        WebElement element = driver.findElement(By.xpath(QUANTITY_ITEMS.toString()));

        scrollPageWithOffset(driver, element, 0, 1000);

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();

        element.click();

        WebElement webElement = driver.findElement(By.xpath("//span[contains(text(),'Показывать по " + quantity + "')]"));

        elementDisplayed(driver, By.xpath("//div[contains(@class(), 'popup_visibility_visible')]"));
        elementDisplayed(driver, webElement);
        elementClickable(driver, webElement);


        driver.findElement(By.xpath("//span[contains(text(),'Показывать по " + quantity + "')]")).click();

        return new YMSubCategoryManufactureQuantityPage(driver);
    }

    public YMSubCategoryManufactureViewPage changeView() {
        driver.findElement(By.xpath(LIST.toString())).click();
        return new YMSubCategoryManufactureViewPage(driver);
    }

    public YMSubCategoryManufactureSortingPricePage sortingPrice(int priceFrom, int priceTo) {
        driver.findElement(By.xpath(FROM.toString())).sendKeys(String.valueOf(priceFrom));
        driver.findElement(By.xpath(TO.toString())).sendKeys(String.valueOf(priceTo));
        driver.findElement(By.xpath("//span[@class='_3RpJHrYdd2']")).click();  // Доставка с учетом цены
        return new YMSubCategoryManufactureSortingPricePage(driver);
    }
}
