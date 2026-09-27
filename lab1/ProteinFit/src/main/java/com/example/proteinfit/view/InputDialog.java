package com.example.proteinfit.view;

import com.example.proteinfit.model.ActivityGoal;
import com.example.proteinfit.model.Gender;
import com.example.proteinfit.model.HeightUnit;
import com.example.proteinfit.model.ProteinModel;
import com.example.proteinfit.model.WeightUnit;
import com.example.proteinfit.view.ui.AppTheme;
import com.example.proteinfit.view.ui.GradientPanel;
import com.example.proteinfit.view.ui.StyledButton;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Locale;
import java.util.Optional;

public class InputDialog extends JDialog {

    private final JTextField weightField = new JTextField(8);
    private final JComboBox<WeightUnit> weightUnitCombo = new JComboBox<>(WeightUnit.values());
    private final JTextField heightField = new JTextField(8);
    private final JComboBox<HeightUnit> heightUnitCombo = new JComboBox<>(HeightUnit.values());
    private final JComboBox<Gender> genderCombo = new JComboBox<>(Gender.values());
    private final JComboBox<ActivityGoal> goalCombo = new JComboBox<>(ActivityGoal.values());
    private boolean confirmed;

    public InputDialog(Frame owner, ProteinModel model) {
        super(owner, "Ввод данных", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        if (model.getWeight() != null) {
            weightField.setText(formatNumber(model.getWeight()));
        }
        if (model.getHeight() != null) {
            heightField.setText(formatNumber(model.getHeight()));
        }
        weightUnitCombo.setSelectedItem(model.getUnit());
        heightUnitCombo.setSelectedItem(model.getHeightUnit());
        genderCombo.setSelectedItem(model.getGender());
        goalCombo.setSelectedItem(model.getGoal());

        styleField(weightField);
        styleField(heightField);
        styleCombo(weightUnitCombo);
        styleCombo(heightUnitCombo);
        styleCombo(genderCombo);
        styleCombo(goalCombo);
        weightUnitCombo.setPreferredSize(new Dimension(100, 40));
        weightUnitCombo.setMaximumSize(new Dimension(100, 40));
        heightUnitCombo.setPreferredSize(new Dimension(100, 40));
        heightUnitCombo.setMaximumSize(new Dimension(100, 40));

        GradientPanel root = new GradientPanel(new BorderLayout(0, 18));
        root.setBorder(BorderFactory.createEmptyBorder(22, 26, 20, 26));

        JLabel title = new JLabel("Введите ваши данные", SwingConstants.CENTER);
        title.setFont(AppTheme.headingFont());
        title.setForeground(AppTheme.PRIMARY_DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(4, 0, 8, 0));

        JPanel titleWrap = new JPanel(new BorderLayout());
        titleWrap.setOpaque(false);
        titleWrap.add(title, BorderLayout.CENTER);

        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(fieldLabel("Вес:"));
        form.add(Box.createVerticalStrut(8));
        form.add(inputRow(weightField, weightUnitCombo));
        form.add(Box.createVerticalStrut(14));

        form.add(fieldLabel("Рост:"));
        form.add(Box.createVerticalStrut(8));
        form.add(inputRow(heightField, heightUnitCombo));
        form.add(Box.createVerticalStrut(14));

        form.add(fieldLabel("Пол:"));
        form.add(Box.createVerticalStrut(8));
        form.add(stretch(genderCombo));
        form.add(Box.createVerticalStrut(14));

        form.add(fieldLabel("Цель / активность:"));
        form.add(Box.createVerticalStrut(8));
        form.add(stretch(goalCombo));

        StyledButton okButton = new StyledButton("OK", true);
        StyledButton cancelButton = new StyledButton("Отмена", false);
        okButton.setPreferredSize(new Dimension(100, 40));
        cancelButton.setPreferredSize(new Dimension(110, 40));
        okButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(okButton);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);
        buttons.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        buttons.add(okButton);
        buttons.add(cancelButton);

        root.add(titleWrap, BorderLayout.NORTH);
        root.add(form, BorderLayout.CENTER);
        root.add(buttons, BorderLayout.SOUTH);

        setContentPane(root);
        pack();
        setSize(Math.max(460, getWidth()), Math.max(520, getHeight()));
        setLocationRelativeTo(owner);
        setResizable(false);
    }

    private static JPanel inputRow(JTextField field, JComboBox<?> unit) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.add(field, BorderLayout.CENTER);
        row.add(unit, BorderLayout.EAST);
        return row;
    }

    private static JPanel stretch(JComponent component) {
        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setOpaque(false);
        wrap.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        wrap.add(component, BorderLayout.CENTER);
        return wrap;
    }

    private static String formatNumber(double value) {
        if (Math.abs(value - Math.rint(value)) < 0.001) {
            return String.format(Locale.US, "%.0f", value);
        }
        return String.format(Locale.US, "%.1f", value);
    }

    private static JLabel fieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.bodyBoldFont());
        label.setForeground(AppTheme.TEXT);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        return label;
    }

    private static void styleField(JTextField field) {
        field.setFont(AppTheme.bodyFont());
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(AppTheme.BORDER, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        field.setBackground(java.awt.Color.WHITE);
        field.setPreferredSize(new Dimension(280, 40));
    }

    private static void styleCombo(JComboBox<?> combo) {
        combo.setFont(AppTheme.bodyFont());
        combo.setBackground(java.awt.Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(AppTheme.BORDER, 1, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        combo.setPreferredSize(new Dimension(280, 40));
    }

    public Optional<InputData> showDialog() {
        setVisible(true);
        if (!confirmed) {
            return Optional.empty();
        }
        return Optional.of(new InputData(
                weightField.getText(),
                (WeightUnit) weightUnitCombo.getSelectedItem(),
                heightField.getText(),
                (HeightUnit) heightUnitCombo.getSelectedItem(),
                (Gender) genderCombo.getSelectedItem(),
                (ActivityGoal) goalCombo.getSelectedItem()
        ));
    }

    public record InputData(
            String weightText,
            WeightUnit unit,
            String heightText,
            HeightUnit heightUnit,
            Gender gender,
            ActivityGoal goal
    ) {
    }
}
