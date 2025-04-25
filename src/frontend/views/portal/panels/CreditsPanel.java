package frontend.views.portal.panels;

import javax.swing.*;

import java.awt.*;

public class CreditsPanel extends JPanel {

    public CreditsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Page Title
        JLabel titleLabel = new JLabel("ISTE 330 - Final Project");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Created By Title
        JLabel createdByLabel = new JLabel("Created by: ");
        createdByLabel.setFont(createdByLabel.getFont().deriveFont(Font.BOLD, 16f));
        createdByLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(createdByLabel);

        // Page Content
        JLabel charles = new JLabel("Database: Charles Coleman");
        charles.setAlignmentX(Component.CENTER_ALIGNMENT);
        charles.setForeground(Color.WHITE);
        add(charles);

        JLabel flavio = new JLabel("Database: Flavio Medina");
        flavio.setAlignmentX(Component.CENTER_ALIGNMENT);
        flavio.setForeground(Color.WHITE);
        add(flavio);

        JLabel sean = new JLabel("Backend: Sean Guyon");
        sean.setAlignmentX(Component.CENTER_ALIGNMENT);
        sean.setForeground(Color.WHITE);
        add(sean);

        JLabel will = new JLabel("Backend: Will Jacobs");
        will.setAlignmentX(Component.CENTER_ALIGNMENT);
        will.setForeground(Color.WHITE);
        add(will);

        JLabel david = new JLabel("Frontend: David Kalinowski");
        david.setAlignmentX(Component.CENTER_ALIGNMENT);
        david.setForeground(Color.WHITE);
        add(david);

    }
}
