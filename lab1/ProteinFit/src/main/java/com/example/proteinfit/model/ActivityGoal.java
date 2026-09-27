package com.example.proteinfit.model;

public enum ActivityGoal {
    MAINTENANCE(
            "Поддержание веса",
            1.4, 1.6,
            1.2, 1.4
    ),
    MODERATE(
            "Умеренные тренировки (2–3 раза в неделю)",
            1.6, 1.8,
            1.4, 1.6
    ),
    STRENGTH(
            "Силовые тренировки / набор массы",
            2.0, 2.4,
            1.8, 2.2
    ),
    CUTTING(
            "Сушка",
            2.2, 2.6,
            2.0, 2.4
    );

    private final String displayName;
    private final double maleMin;
    private final double maleMax;
    private final double femaleMin;
    private final double femaleMax;

    ActivityGoal(String displayName,
                 double maleMin, double maleMax,
                 double femaleMin, double femaleMax) {
        this.displayName = displayName;
        this.maleMin = maleMin;
        this.maleMax = maleMax;
        this.femaleMin = femaleMin;
        this.femaleMax = femaleMax;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double minPerKg(Gender gender) {
        return gender == Gender.FEMALE ? femaleMin : maleMin;
    }

    public double maxPerKg(Gender gender) {
        return gender == Gender.FEMALE ? femaleMax : maleMax;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
