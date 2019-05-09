package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YMSubCategoryManufactureSortingPricePage {
    private WebDriver driver;

    YMSubCategoryManufactureSortingPricePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean priceAccepted() {
        return driver.findElement(By.xpath("//input[@id='glpricefrom' and @value='10000']")).isEnabled() &&
                driver.findElement(By.xpath("//input[@id='glpriceto' and @value='20000']")).isEnabled();
    }
}
