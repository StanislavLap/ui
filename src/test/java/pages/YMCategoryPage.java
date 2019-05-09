package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class YMCategoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    YMCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод для получения заголовка страницы с категорией
     *
     * @return заголовок
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Метод для получения подкатегории
     *
     * @param linkText наименование подкатегории
     * @return подкатегория с требуемым наименованием
     */
    public YMSubCategoryPage selectSubCategory(String linkText) {
        wait = new WebDriverWait(driver, 20);
        wait.until(visibilityOfElementLocated(By.xpath("//a[contains(.,'" + linkText + "')]")));
        driver.findElement(By.xpath("//a[contains(.,'" + linkText + "')]")).click();
        return new YMSubCategoryPage(driver);
    }
}