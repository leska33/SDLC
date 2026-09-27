package com.example.proteinfit.view.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public final class AppTheme {

    public static final Color BG_TOP = new Color(0xF4, 0xF2, 0xFF);
    public static final Color BG_BOTTOM = new Color(0xF8, 0xFA, 0xFF);
    public static final Color CARD = Color.WHITE;
    public static final Color CARD_SOFT = new Color(0xEE, 0xEB, 0xFF);
    public static final Color PRIMARY = new Color(0x6C, 0x63, 0xFF);
    public static final Color PRIMARY_HOVER = new Color(0x5A, 0x52, 0xE8);
    public static final Color PRIMARY_DARK = new Color(0x2E, 0x2A, 0x6E);
    public static final Color TEXT = new Color(0x2A, 0x2F, 0x55);
    public static final Color TEXT_MUTED = new Color(0x6B, 0x72, 0x9A);
    public static final Color BORDER = new Color(0xD8, 0xD4, 0xF5);
    public static final Color SUCCESS_BG = new Color(0xE4, 0xF8, 0xEC);
    public static final Color SUCCESS_FG = new Color(0x1B, 0x7A, 0x3E);
    public static final Color INFO_BG = new Color(0xEB, 0xE8, 0xFF);
    public static final Color INFO_FG = new Color(0x4A, 0x43, 0xC9);
    public static final Color DANGER = new Color(0xEF, 0x44, 0x44);
    public static final Color SHADOW = new Color(80, 70, 160, 28);
    public static final Color ICON = new Color(0x4A, 0x43, 0xC9);

    private AppTheme() {
    }

    public static Font titleFont() {
        return new Font("Segoe UI", Font.BOLD, 26);
    }

    public static Font subtitleFont() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    public static Font headingFont() {
        return new Font("Segoe UI", Font.BOLD, 16);
    }

    public static Font errorTitleFont() {
        return new Font("Segoe UI", Font.BOLD, 20);
    }

    public static Font bodyFont() {
        return new Font("Segoe UI", Font.PLAIN, 13);
    }

    public static Font bodyBoldFont() {
        return new Font("Segoe UI", Font.BOLD, 13);
    }

    public static Font buttonFont() {
        return new Font("Segoe UI", Font.BOLD, 14);
    }

    public static Font valueFont() {
        return new Font("Segoe UI", Font.BOLD, 14);
    }

    public static void enableQuality(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    public static void paintSoftShadow(Graphics2D g, int x, int y, int w, int h, int arc) {
        g.setColor(SHADOW);
        g.fill(new RoundRectangle2D.Float(x + 2, y + 4, w, h, arc, arc));
    }

    public static void drawCenteredString(Graphics2D g, String text, int cx, int cy) {
        FontMetrics fm = g.getFontMetrics();
        g.drawString(text, cx - fm.stringWidth(text) / 2, cy + fm.getAscent() / 2 - 2);
    }
}
