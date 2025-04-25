package frontend.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.util.Map;

public class Stats extends JPanel {
    public Stats(Map<String, String> stats) {
        setLayout(new GridLayout(1, stats.size(), 8, 0));
        putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        setBackground(Color.decode("#272A2D"));
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));

        stats.forEach((title, value) -> {
            JPanel statsItem = StatsItem(title, value);
            statsItem.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
            add(statsItem);
        });
    }

    private JPanel StatsItem(String title, String value) {
        JPanel statsItem = new JPanel();
        statsItem.setOpaque(false);
        statsItem.setLayout(new GridLayout(2, 1, 0, 2));

        statsItem.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 12f));
        titleLabel.setForeground(Color.decode("#6D6F70"));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD, 20f));
        valueLabel.setForeground(Color.decode("#FFFFFF"));

        statsItem.add(titleLabel);
        statsItem.add(valueLabel);

        return statsItem;
    }
}
