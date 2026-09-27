package com.example.proteinfit.controller;

import com.example.proteinfit.model.ProteinModel;
import com.example.proteinfit.view.ErrorDialog;
import com.example.proteinfit.view.InputDialog;
import com.example.proteinfit.view.MainFrame;

public class ProteinController {

    private final ProteinModel model;
    private final MainFrame mainFrame;

    public ProteinController(ProteinModel model, MainFrame mainFrame) {
        this.model = model;
        this.mainFrame = mainFrame;
        this.mainFrame.onEnterData(e -> openInputDialog());
    }

    private void openInputDialog() {
        InputDialog dialog = new InputDialog(mainFrame, model);
        dialog.showDialog().ifPresent(data -> {
            boolean ok = model.applyInput(
                    data.weightText(),
                    data.unit(),
                    data.heightText(),
                    data.heightUnit(),
                    data.gender(),
                    data.goal()
            );
            if (!ok) {
                ErrorDialog.show(mainFrame);
            }
        });
    }
}
