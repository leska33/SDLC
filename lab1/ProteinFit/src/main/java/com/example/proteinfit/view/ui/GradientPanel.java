package com.example.proteinfit.view.ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

public class GradientPanel extends JPanel {

    public GradientPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        AppTheme.enableQuality(g2);
        g2.setPaint(new GradientPaint(0, 0, AppTheme.BG_TOP, 0, getHeight(), AppTheme.BG_BOTTOM));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(new Color(0xC7, 0xD2, 0xFE, 60));
        g2.fillOval(getWidth() - 180, -60, 240, 240);
        g2.fillOval(-80, getHeight() - 160, 220, 220);
        g2.dispose();
        super.paintComponent(g);
    }
}
