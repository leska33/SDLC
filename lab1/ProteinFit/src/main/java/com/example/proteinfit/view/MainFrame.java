package com.example.proteinfit.view;

import com.example.proteinfit.model.ModelListener;
import com.example.proteinfit.model.ProteinModel;
import com.example.proteinfit.view.ui.AppTheme;
import com.example.proteinfit.view.ui.DataRow;
import com.example.proteinfit.view.ui.GradientPanel;
import com.example.proteinfit.view.ui.HeroImagePanel;
import com.example.proteinfit.view.ui.RoundedPanel;
import com.example.proteinfit.view.ui.StyledButton;
import com.example.proteinfit.view.ui.UiIcons;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ModelListener {

    private final ProteinModel model;
    private final StyledButton enterDataButton = new StyledButton(
            "Ввести данные", true, UiIcons.plus(16, java.awt.Color.WHITE));

    private final JPanel emptyStatePanel = new JPanel(new GridBagLayout());
    private final JPanel resultStatePanel = new JPanel(new GridBagLayout());
    private final JPanel centerHost = new JPanel(new BorderLayout());

    private final DataRow lastWeight = new DataRow(UiIcons.scale(18, AppTheme.ICON), "Вес:");
    private final DataRow lastHeight = new DataRow(UiIcons.height(18, AppTheme.ICON), "Рост:");
    private final DataRow lastGender = new DataRow(UiIcons.gender(18, AppTheme.ICON), "Пол:");
    private final DataRow lastGoal = new DataRow(UiIcons.dumbbell(18, AppTheme.ICON), "Цель:");
    private final DataRow lastProtein = new DataRow(UiIcons.flame(18, AppTheme.ICON), "Суточная норма протеина:");

    private final DataRow resultWeight = new DataRow(UiIcons.scale(18, AppTheme.ICON), "Ваш вес:");
    private final DataRow resultHeight = new DataRow(UiIcons.height(18, AppTheme.ICON), "Рост:");
    private final DataRow resultGender = new DataRow(UiIcons.gender(18, AppTheme.ICON), "Пол:");
    private final DataRow resultGoal = new DataRow(UiIcons.dumbbell(18, AppTheme.ICON), "Цель:");
    private final DataRow resultProtein = new DataRow(UiIcons.flame(18, AppTheme.ICON), "Суточная норма протеина:");

    public MainFrame(ProteinModel model) {
        super("ProteinFit");
        this.model = model;
        this.model.addListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(880, 560));
        setSize(940, 600);
        setLocationRelativeTo(null);

        GradientPanel root = new GradientPanel(new BorderLayout(10, 0));
        root.setBorder(BorderFactory.createEmptyBorder(16, 22, 14, 14));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JPanel brand = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        brand.setOpaque(false);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel brandIcon = new JLabel(UiIcons.dumbbell(20, AppTheme.PRIMARY));
        brandIcon.setVerticalAlignment(SwingConstants.CENTER);
        brand.add(brandIcon);
        JLabel brandName = new JLabel("ProteinFit");
        brandName.setFont(AppTheme.headingFont());
        brandName.setForeground(AppTheme.PRIMARY);
        brand.add(brandName);

        JLabel title = new JLabel("Калькулятор суточной нормы протеина");
        title.setFont(AppTheme.titleFont());
        title.setForeground(AppTheme.PRIMARY_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "<html>Узнайте, сколько протеина вам нужно для поддержания<br>здоровья и достижения целей</html>");
        subtitle.setFont(AppTheme.subtitleFont());
        subtitle.setForeground(AppTheme.TEXT_MUTED);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(brand);
        header.add(Box.createVerticalStrut(10));
        header.add(title);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitle);

        buildEmptyState();
        buildResultState();

        centerHost.setOpaque(false);
        centerHost.add(emptyStatePanel, BorderLayout.CENTER);

        HeroImagePanel hero = new HeroImagePanel("/images/hero_fitness.png", 300, 460);
        JPanel heroWrap = new JPanel(new BorderLayout());
        heroWrap.setOpaque(false);
        heroWrap.setPreferredSize(new Dimension(310, 470));
        heroWrap.add(hero, BorderLayout.CENTER);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonRow.setOpaque(false);
        buttonRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 6, 0));
        buttonRow.add(enterDataButton);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        footer.setOpaque(false);
        JLabel footerLabel = new JLabel(
                "Здоровое питание — залог энергии и хорошего самочувствия",
                UiIcons.leaf(16, AppTheme.PRIMARY),
                SwingConstants.LEFT
        );
        footerLabel.setIconTextGap(8);
        footerLabel.setFont(AppTheme.bodyFont());
        footerLabel.setForeground(AppTheme.TEXT_MUTED);
        footerLabel.setVerticalAlignment(SwingConstants.CENTER);
        footer.add(footerLabel);

        JPanel south = new JPanel(new BorderLayout());
        south.setOpaque(false);
        south.add(buttonRow, BorderLayout.NORTH);
        south.add(footer, BorderLayout.SOUTH);

        JPanel leftColumn = new JPanel(new BorderLayout(0, 0));
        leftColumn.setOpaque(false);
        leftColumn.add(header, BorderLayout.NORTH);
        leftColumn.add(centerHost, BorderLayout.CENTER);
        leftColumn.add(south, BorderLayout.SOUTH);

        root.add(leftColumn, BorderLayout.CENTER);
        root.add(heroWrap, BorderLayout.EAST);

        setContentPane(root);
        modelChanged(model);
    }

    private void buildEmptyState() {
        emptyStatePanel.setOpaque(false);
        emptyStatePanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

        RoundedPanel lastCard = new RoundedPanel(new BorderLayout(), AppTheme.CARD_SOFT, 18, false);
        lastCard.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));

        JLabel cardTitle = new JLabel("Последние данные");
        cardTitle.setFont(AppTheme.headingFont());
        cardTitle.setForeground(AppTheme.PRIMARY_DARK);
        cardTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        lastCard.add(cardTitle, BorderLayout.NORTH);
        lastCard.add(column(lastWeight, lastHeight, lastGender, lastGoal, lastProtein), BorderLayout.CENTER);
        fitCardWidth(lastCard, 460);

        GridBagConstraints gbc = leftCardConstraints();
        emptyStatePanel.add(lastCard, gbc);
    }

    private void buildResultState() {
        resultStatePanel.setOpaque(false);
        resultStatePanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));

        RoundedPanel card = new RoundedPanel(new BorderLayout(), AppTheme.CARD, 18, true);

        JPanel success = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        success.setBackground(AppTheme.SUCCESS_BG);
        success.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
        JLabel successLabel = new JLabel(
                "Данные сохранены!",
                UiIcons.check(18, AppTheme.SUCCESS_FG),
                SwingConstants.LEFT
        );
        successLabel.setIconTextGap(8);
        successLabel.setFont(AppTheme.bodyBoldFont());
        successLabel.setForeground(AppTheme.SUCCESS_FG);
        successLabel.setVerticalAlignment(SwingConstants.CENTER);
        success.add(successLabel);

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        body.add(column(resultWeight, resultHeight, resultGender, resultGoal, resultProtein));

        card.add(success, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        fitCardWidth(card, 470);

        resultStatePanel.add(card, leftCardConstraints());
    }

    private static GridBagConstraints leftCardConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private static void fitCardWidth(JPanel card, int width) {
        card.doLayout();
        Dimension pref = card.getPreferredSize();
        card.setPreferredSize(new Dimension(width, pref.height));
        card.setMaximumSize(new Dimension(width, pref.height));
    }

    private static JPanel column(DataRow... rows) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (int i = 0; i < rows.length; i++) {
            rows[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(rows[i]);
            if (i < rows.length - 1) {
                panel.add(Box.createVerticalStrut(8));
            }
        }
        return panel;
    }

    public void onEnterData(ActionListener listener) {
        enterDataButton.addActionListener(listener);
    }

    @Override
    public void modelChanged(ProteinModel updatedModel) {
        String weight = updatedModel.hasResult() ? updatedModel.formatWeightValue() : "—";
        String height = updatedModel.hasResult() ? updatedModel.formatHeight() : "—";
        String gender = updatedModel.hasResult() ? updatedModel.formatGender() : "—";
        String goal = updatedModel.hasResult() ? updatedModel.formatGoal() : "—";
        String protein = updatedModel.hasResult() ? updatedModel.formatDailyProtein() : "—";

        lastWeight.setValue(weight);
        lastHeight.setValue(height);
        lastGender.setValue(gender);
        lastGoal.setValue(goal);
        lastProtein.setValue(protein);

        resultWeight.setValue(weight);
        resultHeight.setValue(height);
        resultGender.setValue(gender);
        resultGoal.setValue(goal);
        resultProtein.setValue(protein);
        resultProtein.getValueLabel().setForeground(AppTheme.PRIMARY);

        centerHost.removeAll();
        enterDataButton.setLeadingIcon(UiIcons.plus(16, java.awt.Color.WHITE));
        if (updatedModel.hasResult()) {
            centerHost.add(resultStatePanel, BorderLayout.CENTER);
        } else {
            centerHost.add(emptyStatePanel, BorderLayout.CENTER);
        }
        centerHost.revalidate();
        centerHost.repaint();
    }
}
