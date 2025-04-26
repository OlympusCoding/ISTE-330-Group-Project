package frontend.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.*;

public class Searchbar extends JPanel {
    private JTextField searchField;
    private JButton searchButton;

    public Searchbar() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(2, 8, 8, 8));
        setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        searchField = new JTextField("Search");
        searchField.setBackground(Color.decode("#272A2D"));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        searchField.setPreferredSize(new Dimension(0, 32));
        searchField.setMinimumSize(new Dimension(0, 32));
        searchField.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search");
                }
            }
        });

        searchButton = new JButton(new ImageIcon(getClass().getResource("/frontend/assets/images/search_active.png")));
        searchButton.setBackground(Color.decode("#272A2D"));
        searchButton.setPreferredSize(new Dimension(32, 32));
        searchButton.setMaximumSize(new Dimension(32, 32));
        searchButton.setMinimumSize(new Dimension(32, 32));
        searchButton.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
        add(searchField);
        add(Box.createRigidArea(new Dimension(4, 0)));
        add(searchButton);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
