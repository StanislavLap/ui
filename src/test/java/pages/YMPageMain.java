package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static base.Common.outputFindingElement;

public class YMPageMain {
    private WebDriver driver;
    private WebElement we;

    public YMPageMain(WebDriver driver) {
        this.driver = driver;
    }

    public YMCategoryPage selectCategory(String linkText) {

            WebDriverWait webDriverWait = new WebDriverWait(driver, 40);
            we = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[contains(.,'" + linkText + "')]")));
            if(we != null){
                outputFindingElement("//a/span[contains(text(),'" + linkText + "')]");
                we.click();
            }

        return new YMCategoryPage(driver);
    }
}
