package com.example.resellerapp.model.enums;

public enum ConditionEnum {

    EXCELLENT ("In perfect condition"),
    GOOD ("Some signs of wear and tear or minor defects"),
    ACCEPTABLE ("The item is fairly worn but continues to function properly");

    private final String value;

    private ConditionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
