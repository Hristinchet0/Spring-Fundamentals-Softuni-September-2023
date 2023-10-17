package com.example.plannerapp.model.enums;

public enum PriorityEnum {

    URGENT ("An urgent problem that blocks the system use until the issue is resolved."),
    IMPORTANT ("A core functionality that your product is explicitly supposed to perform is compromised."),
    LOW ("Should be fixed if time permits but can be postponed.");

    private final String value;

    private PriorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
