package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YMSubCategoryManufactureViewPage {
    private WebDriver driver;

    public YMSubCategoryManufactureViewPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isView(String view) {
        return driver.findElement(By.xpath("//input[contains(@value, '" + view + "')]/ancestor::label[contains(@class, 'checked')]")).isEnabled();
    }
}
