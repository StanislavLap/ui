package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YMSubCategoryPage {
    private final WebDriver driver;

    YMSubCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Метод для получения отсортирвоанной по производителю страницы
     * @param manufactureName наименование производителя
     * @return подкатегория с требуемым производителем
     */
    public YMSubCategoryManufacturePage sortByManufacture(String manufactureName) {
        driver.findElement(By.xpath("//a[@class='_2RDCAZB4Gk']//span[@class='NVoaOvqe58'][contains(text(),'" + manufactureName + "')]")).click();
        return new YMSubCategoryManufacturePage(driver);
    }
}
