package locators;

public enum YMSubCategoryManufactureViewPage {
    GRID("//label//input[contains(@value, 'grid')]"),
    LIST("//label//input[contains(@value, 'list')]");

    private final String value;

    YMSubCategoryManufactureViewPage(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}