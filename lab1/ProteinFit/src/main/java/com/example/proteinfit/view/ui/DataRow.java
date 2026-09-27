package com.example.proteinfit.view.ui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class DataRow extends JPanel {

    private static final int ROW_H = 30;
    private static final int ICON_COL = 28;
    private static final int LABEL_COL = 230;

    private final JLabel valueLabel;

    public DataRow(Icon icon, String caption) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(440, ROW_H));
        setMinimumSize(new Dimension(320, ROW_H));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, ROW_H));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(ICON_COL, ROW_H));
        iconLabel.setMinimumSize(new Dimension(ICON_COL, ROW_H));
        iconLabel.setMaximumSize(new Dimension(ICON_COL, ROW_H));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 12);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(iconLabel, gbc);

        JLabel captionLabel = new JLabel(caption);
        captionLabel.setFont(AppTheme.bodyFont());
        captionLabel.setForeground(AppTheme.TEXT_MUTED);
        captionLabel.setPreferredSize(new Dimension(LABEL_COL, ROW_H));
        captionLabel.setMinimumSize(new Dimension(LABEL_COL, ROW_H));
        captionLabel.setVerticalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 0, 8);
        add(captionLabel, gbc);

        valueLabel = new JLabel("—");
        valueLabel.setFont(AppTheme.valueFont());
        valueLabel.setForeground(AppTheme.TEXT);
        valueLabel.setHorizontalAlignment(SwingConstants.LEFT);
        valueLabel.setVerticalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(valueLabel, gbc);
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }

    public JLabel getValueLabel() {
        return valueLabel;
    }
}
