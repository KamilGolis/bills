package pl.bills.enums;

/*
  Created by trot on 23.01.17.
 */

/**
 * Required categories.
 * MAIN for every bill if other categories are not available
 * TRASH for deleted but not removed bills.
 */
public enum CategoryEnum {
    MAIN("Główna"),
    TRASH("Kosz");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public String get() {
        return category;
    }
}
