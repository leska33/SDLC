package com.example.proteinfit.view.ui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    private final int arc;
    private final Color fill;
    private final boolean shadow;

    public RoundedPanel(LayoutManager layout, Color fill, int arc, boolean shadow) {
        super(layout);
        this.fill = fill;
        this.arc = arc;
        this.shadow = shadow;
        setOpaque(false);
        if (shadow) {
            setBorder(BorderFactory.createEmptyBorder(3, 0, 6, 6));
        }
    }

    public RoundedPanel(LayoutManager layout, Color fill) {
        this(layout, fill, 18, true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        AppTheme.enableQuality(g2);
        int left = 0;
        int top = shadow ? 3 : 0;
        int right = shadow ? 6 : 0;
        int bottom = shadow ? 6 : 0;
        int w = getWidth() - left - right;
        int h = getHeight() - top - bottom;
        if (shadow) {
            AppTheme.paintSoftShadow(g2, left, top, w, h, arc);
        }
        g2.setColor(fill);
        g2.fill(new RoundRectangle2D.Float(left, top, w, h, arc, arc));
        g2.dispose();
        super.paintComponent(g);
    }
}
