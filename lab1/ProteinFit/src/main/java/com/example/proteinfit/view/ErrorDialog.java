package com.example.proteinfit.view;

import com.example.proteinfit.view.ui.AppTheme;
import com.example.proteinfit.view.ui.GradientPanel;
import com.example.proteinfit.view.ui.StyledButton;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class ErrorDialog extends JDialog {

    public ErrorDialog(Frame owner) {
        super(owner, "Ошибка", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GradientPanel root = new GradientPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(28, 28, 20, 28));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel icon = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                AppTheme.enableQuality(g2);
                g2.setColor(AppTheme.DANGER);
                g2.fill(new Ellipse2D.Float(4, 4, 40, 40));
                g2.setColor(java.awt.Color.WHITE);
                g2.setStroke(new BasicStroke(3.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.draw(new Line2D.Float(24, 14, 24, 30));
                g2.fill(new Ellipse2D.Float(21.5f, 34, 5, 5));
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(48, 48);
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        icon.setOpaque(false);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Некорректные данные!", SwingConstants.CENTER);
        title.setFont(AppTheme.errorTitleFont());
        title.setForeground(AppTheme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getPreferredSize().height));

        center.add(Box.createVerticalGlue());
        center.add(icon);
        center.add(Box.createVerticalStrut(18));
        center.add(title);
        center.add(Box.createVerticalGlue());

        StyledButton ok = new StyledButton("OK", true);
        ok.setPreferredSize(new Dimension(100, 38));
        ok.addActionListener(e -> dispose());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttons.setOpaque(false);
        buttons.add(ok);

        root.add(center, BorderLayout.CENTER);
        root.add(buttons, BorderLayout.SOUTH);

        setContentPane(root);
        setSize(360, 220);
        setLocationRelativeTo(owner);
        setResizable(false);
    }

    public static void show(Frame owner) {
        new ErrorDialog(owner).setVisible(true);
    }
}
