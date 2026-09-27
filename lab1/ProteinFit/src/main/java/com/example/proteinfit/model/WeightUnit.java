package com.example.proteinfit.model;

public enum WeightUnit {
    KG("кг"),
    LB("фунты");

    private final String displayName;

    WeightUnit(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
