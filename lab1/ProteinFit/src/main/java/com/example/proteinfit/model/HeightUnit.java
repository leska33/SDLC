package com.example.proteinfit.model;

public enum HeightUnit {
    CM("см"),
    INCH("дюймы");

    private final String displayName;

    HeightUnit(String displayName) {
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
