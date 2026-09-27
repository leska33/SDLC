package com.example.proteinfit.view.ui;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

public class StyledButton extends JButton {

    private static final int H_PAD = 18;
    private static final int ICON_GAP = 10;

    private final boolean primary;
    private boolean hovered;
    private Icon leadingIcon;

    public StyledButton(String text, boolean primary) {
        this(text, primary, null);
    }

    public StyledButton(String text, boolean primary, Icon leadingIcon) {
        super(text);
        this.primary = primary;
        this.leadingIcon = leadingIcon;
        setFont(AppTheme.buttonFont());
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, H_PAD, 0, H_PAD));
        setMargin(new Insets(0, H_PAD, 0, H_PAD));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(preferredForContent());

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public void setLeadingIcon(Icon leadingIcon) {
        this.leadingIcon = leadingIcon;
        setPreferredSize(preferredForContent());
        revalidate();
        repaint();
    }

    private Dimension preferredForContent() {
        FontMetrics fm = getFontMetrics(getFont());
        int textW = fm.stringWidth(getText() == null ? "" : getText());
        int iconW = leadingIcon != null ? leadingIcon.getIconWidth() + ICON_GAP : 0;
        int width = Math.max(primary ? 210 : 120, textW + iconW + H_PAD * 2);
        return new Dimension(width, primary ? 46 : 40);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension content = preferredForContent();
        Dimension current = super.getPreferredSize();
        return new Dimension(Math.max(content.width, current.width), Math.max(content.height, current.height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        AppTheme.enableQuality(g2);
        int arc = 16;
        if (primary) {
            g2.setColor(hovered ? AppTheme.PRIMARY_HOVER : AppTheme.PRIMARY);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc));
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc));
            g2.setColor(AppTheme.PRIMARY);
            g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 3, getHeight() - 3, arc, arc));
            g2.setColor(AppTheme.PRIMARY);
        }

        String text = getText();
        FontMetrics fm = g2.getFontMetrics();
        int iconW = leadingIcon != null ? leadingIcon.getIconWidth() : 0;
        int iconGap = leadingIcon != null ? ICON_GAP : 0;
        int contentWidth = fm.stringWidth(text) + iconW + iconGap;
        int startX = Math.max(H_PAD, (getWidth() - contentWidth) / 2);
        int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        if (leadingIcon != null) {
            int iconY = (getHeight() - leadingIcon.getIconHeight()) / 2;
            leadingIcon.paintIcon(this, g2, startX, iconY);
            startX += iconW + iconGap;
        }
        g2.drawString(text, startX, textY);
        g2.dispose();
    }
}
