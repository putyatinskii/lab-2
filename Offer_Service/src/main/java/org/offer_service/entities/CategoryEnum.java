package org.offer_service.entities;

public enum CategoryEnum {
    CATEGORY1("category1"),
    CATEGORY2("category2"),
    CATEGORY3("category3"),
    CATEGORY4("category4"),
    CATEGORY5("category5");

    private String name;

    CategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
