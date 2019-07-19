package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static base.Common.*;
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

    public YMSubCategoryManufactureQuantityPage selectQuantity(int quantity) throws InterruptedException {

        waitForJSandJQueryToLoad(driver);

        System.out.println("Скролим до //span[contains(text(),'Показать еще')]");
        scrollPageToWebElementByXpath(driver, driver.findElement(By.xpath("//span[contains(text(),'Показать еще')]")));


        //if (elementClickable(driver, driver.findElement(By.xpath(QUANTITY_ITEMS.toString())))) { //TODO не кликает по кнопке выбора количества элементов на странице
        if (elementClickable(driver, driver.findElement(By.xpath("//span[contains(text(),'Показывать по')]/ancestor::button")))) { //TODO не кликает по кнопке выбора количества элементов на странице
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(visibilityOfElementLocated(By.xpath("//button[@class='button button_theme_normal button_arrow_down button_size_s select__button i-bem button_js_inited']")));
            System.out.println("Кликаем по выбору количества элементов");
            Thread.sleep(5000);
            scrollPageToWebElementByXpath(driver, driver.findElement(By.xpath("//span[contains(text(),'Показывать по')]/ancestor::button")));
            driver.findElement(By.xpath("//span[contains(text(),'Показывать по')]/ancestor::button")).click();
        }

        if (elementClickable(driver, driver.findElement(By.xpath("//div/span[contains(text(),'Показывать по " + quantity + "')]")))) {
            assertThatElementDisplayed(driver, driver.findElement(By.xpath("//div/span[contains(text(),'Показывать по " + quantity + "')]")));
            Actions actions2 = new Actions(driver);
            actions2.moveToElement(driver.findElement(By.xpath("//div/span[contains(text(),'Показывать по " + quantity + "')]"))).click().build().perform();
        }

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
