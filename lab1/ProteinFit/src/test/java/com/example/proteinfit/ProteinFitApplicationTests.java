package com.example.proteinfit;

import com.example.proteinfit.model.ActivityGoal;
import com.example.proteinfit.model.Gender;
import com.example.proteinfit.model.HeightUnit;
import com.example.proteinfit.model.ProteinModel;
import com.example.proteinfit.model.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProteinFitApplicationTests {

    @Test
    void calculatesByGoalAndGenderMaleMaintenance() {
        ProteinModel model = new ProteinModel();
        assertTrue(model.applyInput(
                "70", WeightUnit.KG, "175", HeightUnit.CM,
                Gender.MALE, ActivityGoal.MAINTENANCE));
        assertEquals(70 * 1.4, model.getMinProteinGrams(), 0.001);
        assertEquals(70 * 1.6, model.getMaxProteinGrams(), 0.001);
        assertEquals("98 – 112 г", model.formatDailyProtein());
    }

    @Test
    void calculatesByGoalAndGenderFemaleCutting() {
        ProteinModel model = new ProteinModel();
        assertTrue(model.applyInput(
                "60", WeightUnit.KG, "165", HeightUnit.CM,
                Gender.FEMALE, ActivityGoal.CUTTING));
        assertEquals(60 * 2.0, model.getMinProteinGrams(), 0.001);
        assertEquals(60 * 2.4, model.getMaxProteinGrams(), 0.001);
    }

    @Test
    void calculatesStrengthMale() {
        ProteinModel model = new ProteinModel();
        assertTrue(model.applyInput(
                "80", WeightUnit.KG, "180", HeightUnit.CM,
                Gender.MALE, ActivityGoal.STRENGTH));
        assertEquals(80 * 2.0, model.getMinProteinGrams(), 0.001);
        assertEquals(80 * 2.4, model.getMaxProteinGrams(), 0.001);
    }

    @Test
    void convertsHeightInchesToCm() {
        ProteinModel model = new ProteinModel();
        assertTrue(model.applyInput(
                "70", WeightUnit.KG, "67", HeightUnit.INCH,
                Gender.FEMALE, ActivityGoal.MODERATE));
        assertEquals(67 * 2.54, model.getHeightCm(), 0.01);
    }

    @Test
    void rejectsInvalidInput() {
        ProteinModel model = new ProteinModel();
        assertFalse(model.applyInput(
                "abc", WeightUnit.KG, "170", HeightUnit.CM,
                Gender.MALE, ActivityGoal.MAINTENANCE));
        assertFalse(model.applyInput(
                "70", WeightUnit.KG, "", HeightUnit.CM,
                Gender.MALE, ActivityGoal.MAINTENANCE));
    }
}
