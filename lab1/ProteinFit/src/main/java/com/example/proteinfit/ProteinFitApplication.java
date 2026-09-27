package com.example.proteinfit;

import com.example.proteinfit.controller.ProteinController;
import com.example.proteinfit.model.ProteinModel;
import com.example.proteinfit.view.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ProteinFitApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            UIManager.put("Label.font", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

            ProteinModel model = new ProteinModel();
            MainFrame view = new MainFrame(model);
            new ProteinController(model, view);
            view.setVisible(true);
        });
    }
}
