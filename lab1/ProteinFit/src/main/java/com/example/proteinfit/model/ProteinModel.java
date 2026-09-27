package com.example.proteinfit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProteinModel {

    private static final double LB_TO_KG = 0.45359237;
    private static final double INCH_TO_CM = 2.54;

    private final List<ModelListener> listeners = new ArrayList<>();

    private Double weight;
    private Double height;
    private HeightUnit heightUnit = HeightUnit.CM;
    private Gender gender = Gender.MALE;
    private ActivityGoal goal = ActivityGoal.MAINTENANCE;
    private WeightUnit unit = WeightUnit.KG;
    private Double minProteinGrams;
    private Double maxProteinGrams;
    private String lastError;

    public void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ModelListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : List.copyOf(listeners)) {
            listener.modelChanged(this);
        }
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Double getHeightCm() {
        if (height == null) {
            return null;
        }
        return toCentimeters(height, heightUnit);
    }

    public HeightUnit getHeightUnit() {
        return heightUnit;
    }

    public Gender getGender() {
        return gender;
    }

    public ActivityGoal getGoal() {
        return goal;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public Double getMinProteinGrams() {
        return minProteinGrams;
    }

    public Double getMaxProteinGrams() {
        return maxProteinGrams;
    }

    public String getLastError() {
        return lastError;
    }

    public boolean hasResult() {
        return minProteinGrams != null && maxProteinGrams != null;
    }

    public boolean applyInput(String weightText, WeightUnit selectedUnit,
                              String heightText, HeightUnit selectedHeightUnit,
                              Gender selectedGender, ActivityGoal selectedGoal) {
        lastError = null;

        Double parsedWeight = parsePositive(weightText, "вес");
        if (parsedWeight == null) {
            return false;
        }
        if (parsedWeight > 1000) {
            lastError = "Указан нереалистично большой вес.";
            return false;
        }

        Double parsedHeight = parsePositive(heightText, "рост");
        if (parsedHeight == null) {
            return false;
        }

        HeightUnit hUnit = selectedHeightUnit != null ? selectedHeightUnit : HeightUnit.CM;
        double heightCm = toCentimeters(parsedHeight, hUnit);
        if (heightCm < 50 || heightCm > 280) {
            lastError = "Пожалуйста, введите корректный рост.\nДопустимые значения: > 0.";
            return false;
        }

        if (selectedGender == null) {
            lastError = "Выберите пол.";
            return false;
        }
        if (selectedGoal == null) {
            lastError = "Выберите цель.";
            return false;
        }

        this.weight = parsedWeight;
        this.height = parsedHeight;
        this.heightUnit = hUnit;
        this.gender = selectedGender;
        this.goal = selectedGoal;
        this.unit = selectedUnit != null ? selectedUnit : WeightUnit.KG;

        double weightKg = toKilograms(this.weight, this.unit);
        this.minProteinGrams = weightKg * goal.minPerKg(gender);
        this.maxProteinGrams = weightKg * goal.maxPerKg(gender);

        notifyListeners();
        return true;
    }

    private Double parsePositive(String text, String fieldName) {
        if (text == null || text.isBlank()) {
            lastError = "Введите " + fieldName + ".";
            return null;
        }
        String normalized = text.trim().replace(',', '.');
        double value;
        try {
            value = Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            lastError = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)
                    + " должен быть числом.";
            return null;
        }
        if (value <= 0) {
            lastError = "Пожалуйста, введите корректный " + fieldName
                    + ".\nДопустимые значения: > 0.";
            return null;
        }
        return value;
    }

    public static double toKilograms(double weight, WeightUnit unit) {
        return unit == WeightUnit.LB ? weight * LB_TO_KG : weight;
    }

    public static double toCentimeters(double height, HeightUnit unit) {
        return unit == HeightUnit.INCH ? height * INCH_TO_CM : height;
    }

    public String formatWeightValue() {
        if (weight == null) {
            return "—";
        }
        String unitLabel = unit == WeightUnit.KG ? "кг" : "фунта";
        if (unit == WeightUnit.LB) {
            return String.format(Locale.US, "%.1f %s", weight, unitLabel);
        }
        return String.format(Locale.US, "%.0f %s", weight, unitLabel);
    }

    public String formatHeight() {
        if (height == null) {
            return "—";
        }
        String label = heightUnit == HeightUnit.CM ? "см" : "дюйм.";
        return String.format(Locale.US, "%.0f %s", height, label);
    }

    public String formatGender() {
        return gender != null ? gender.getDisplayName() : "—";
    }

    public String formatGoal() {
        return goal != null ? goal.getDisplayName() : "—";
    }

    public String formatUnit() {
        return unit.getDisplayName();
    }

    public String formatCoefficient() {
        if (goal == null || gender == null) {
            return "—";
        }
        return String.format(Locale.US, "%.1f – %.1f г/кг", goal.minPerKg(gender), goal.maxPerKg(gender));
    }

    public String formatDailyProtein() {
        if (!hasResult()) {
            return "—";
        }
        return String.format(Locale.US, "%.0f – %.0f г", minProteinGrams, maxProteinGrams);
    }
}
