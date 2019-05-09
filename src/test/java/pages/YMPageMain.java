package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.WaitElemMaven.waitElem;

public class YMPageMain {
    private WebDriver driver;

    public YMPageMain(WebDriver driver) {
        this.driver = driver;
    }

    public YMCategoryPage selectCategory(String linkText) {
        try {
            waitElem(driver, By.xpath("//a//span[contains(text(),'" + linkText + "')]"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//a//span[contains(text(),'" + linkText + "')]")).click();
        return new YMCategoryPage(driver);
    }
}
