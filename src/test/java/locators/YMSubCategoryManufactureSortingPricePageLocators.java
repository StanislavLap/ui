package locators;

public enum YMSubCategoryManufactureSortingPricePageLocators {
    FROM("//input[@id='glpricefrom']"),
    TO("//input[@id='glpriceto']");

    private final String value;

    YMSubCategoryManufactureSortingPricePageLocators(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}