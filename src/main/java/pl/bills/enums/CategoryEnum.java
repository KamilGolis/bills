package pl.bills.enums;

/**
 * Created by trot on 23.01.17.
 */
public enum CategoryEnum {
    MAIN("main"),
    TRASH("trash");

    private String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public String get() {
        return category;
    }
}
