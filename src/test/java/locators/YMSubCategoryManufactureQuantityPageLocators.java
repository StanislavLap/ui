package locators;

public enum YMSubCategoryManufactureQuantityPageLocators {
    QUANTITY_COUNT("//img[contains(@title,'Варочная панель')]");

    private final String value;

    YMSubCategoryManufactureQuantityPageLocators(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}