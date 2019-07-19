package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static base.Common.selectDriver;
import static base.JsonSimpleParser.getJsonAsMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Stepdefs {
    private WebDriver driver;
    private HashMap<String, String> map;

    private YMCategoryPage ymCategoryPage;
    private YMSubCategoryPage ymSubCategoryPage;
    private YMSubCategoryManufacturePage ymSubCategoryManufacturePage;
    private YMSubCategoryManufactureQuantityPage ymSubCategoryManufactureQuantityPage;
    private YMSubCategoryManufactureViewPage ymSubCategoryManufactureViewPage;
    private YMSubCategoryManufactureSortingPricePage ymSubCategoryManufactureSortingPricePage;

    @Given("^Open chrome$")
    public void open_browser() {
        driver = selectDriver(System.getProperty("driver"));
        driver.manage().window().maximize();
        map = getJsonAsMap();

        // устанавливаем таймаут ожидания загрузки
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

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
    public void iSelectQuantity(int quantity) throws InterruptedException {
        ymSubCategoryManufactureQuantityPage = ymSubCategoryManufacturePage.selectQuantity(quantity);
    }

    @Then("^Number items on page equals (\\d+)$")
    public void numberItemsOnPageEquals(int quantity) throws InterruptedException {
        assertThat(ymSubCategoryManufactureQuantityPage.getQuantityGoods(quantity)).isEqualTo(quantity);
    }

    @Then("^Chrome should be closed$")
    public void chrome_should_be_closed() {
        driver.close();
        driver.quit();
    }

    @When("^I change view to list$")
    public void iChangeViewToList() {
        ymSubCategoryManufactureViewPage = ymSubCategoryManufacturePage.changeView();
    }

    @Then("^View changed$")
    public void viewChanged() {
        Assert.assertTrue(ymSubCategoryManufactureViewPage.isView("list"));
    }

    @When("^I change price range from (\\d+) to (\\d+)$")
    public void iChangePriceRangeFromTo(int priceFrom, int priceTo) {
        ymSubCategoryManufactureSortingPricePage = ymSubCategoryManufacturePage.sortingPrice(priceFrom, priceTo);
    }

    @Then("^Price range changed$")
    public void priceRangeChanged() {
        Assert.assertTrue(ymSubCategoryManufactureSortingPricePage.priceAccepted());
    }

    @After
    public void closeDriver(Scenario scenario) {
        if (scenario.isFailed()) {
            driver.close();
            driver.quit();
        }
    }
}