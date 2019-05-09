package locators;

public enum YMSubCategoryManufacturePageLocators {
    TITLE("//li[@title=\"Встраиваемые варочные панели Bosch\"]"),
    QUANTITY_ITEMS("//span//button[@type='button' and @role='listbox']/span[text()[contains(.,'Показывать')]]/ancestor::button[1]");

    private final String value;

    YMSubCategoryManufacturePageLocators(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}