package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static base.JsonSimpleParser.getJsonAsMap;

public class Stepdefs {
    private WebDriver driver;
    private Map<String, String> map;

    private YMCategoryPage ymCategoryPage;
    private YMSubCategoryPage ymSubCategoryPage;
    private YMSubCategoryManufacturePage ymSubCategoryManufacturePage;
    private YMSubCategoryManufactureQuantityPage ymSubCategoryManufactureQuantityPage;
    private YMSubCategoryManufactureViewPage ymSubCategoryManufactureViewPage;
    private YMSubCategoryManufactureSortingPricePage ymSubCategoryManufactureSortingPricePage;

    @Given("^Open chrome$")
    public void open_chrome() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver246.exe");
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/chropath/ChroPath503.crx"));
        driver = new ChromeDriver(options);
/*        System.setProperty("webdriver.gecko.driver","src/geckodriver0240.exe");
        driver = new FirefoxDriver();*/
        driver.manage().window().maximize();
        map = getJsonAsMap();
        // устанавливаем таймаут ожидания загрузки
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(map.get("url"));
    }

    @When("^I go YM and click on button with category$")
    public void i_go_YM_and_click_on_button_with_category() {
        YMPageMain ymPageMain = new YMPageMain(driver);
        ymCategoryPage = ymPageMain.selectCategory(map.get("selectCategory.category"));
    }

    @Then("^Show correct category title$")
    public void showCorrectCategoryTitle() {
        Assert.assertEquals(map.get("selectCategory.title"), ymCategoryPage.getTitle());
    }

    @When("^I click on subcategory$")
    public void iClickOnSubcategory() {
        ymSubCategoryPage = ymCategoryPage.selectSubCategory(map.get("selectSubCategory.category"));
    }

    @Then("^Show correct subcategory title$")
    public void showCorrectSubcategoryTitle() {
        //Assert.assertEquals(map.get("selectSubCategory.title"), ymSubCategoryPage.getTitle());
        Assert.assertTrue(ymSubCategoryPage.getTitle().contains(map.get("selectSubCategory.title")));
    }

    @When("^I click on bosch manufacturer$")
    public void iClickOnBoschManufacturer() {
        ymSubCategoryManufacturePage = ymSubCategoryPage.sortByManufacture(map.get("sorting.Bosh"));
    }

    @Then("^Show correct bosch manufacturer title$")
    public void showCorrectBoschManufacturerTitle() {
        Assert.assertEquals(map.get("sorting.Bosh.title"), ymSubCategoryManufacturePage.getTitle());
    }

    @When("^I select quantity (\\d+)$")
    public void iSelectQuantity(int quantity) {
        ymSubCategoryManufactureQuantityPage = ymSubCategoryManufacturePage.selectQuantity(quantity);
    }

    @Then("^Number items on page equals (\\d+)$")
    public void numberItemsOnPageEquals(int quantity) {
        Assert.assertEquals(String.valueOf(quantity), ymSubCategoryManufactureQuantityPage.getQuantityGoods());
    }

    @Then("^Chrome should be closed$")
    public void chrome_should_be_closed() {
        driver.close();
        driver.quit();
    }

    @After
    public void closeDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            //byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            //scenario.embed(screenshot, "image/png");
            driver.close();
            driver.quit();
        }
    }

    @When("^I change view to list$")
    public void iChangeViewToList() {
        ymSubCategoryManufactureViewPage = ymSubCategoryManufacturePage.changeView();
    }

    @Then("^View changed$")
    public void viewChanged() {
        Assert.assertTrue(ymSubCategoryManufactureViewPage.isView("List"));
    }

    @When("^I change price range from (\\d+) to (\\d+)$")
    public void iChangePriceRangeFromTo(int priceFrom, int priceTo) {
        ymSubCategoryManufactureSortingPricePage = ymSubCategoryManufacturePage.sortingPrice(priceFrom, priceTo);
    }

    @Then("^Price range changed$")
    public void priceRangeChanged() {
        Assert.assertTrue(ymSubCategoryManufactureSortingPricePage.priceAccepted());
    }
}