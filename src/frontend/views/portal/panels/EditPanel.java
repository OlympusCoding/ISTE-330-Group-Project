package frontend.views.portal.panels;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;

import backend.GUIFascade;
import frontend.views.portal.Portal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPanel extends JPanel {

    private GUIFascade fascade;

    private JLabel abstractText;

    public EditPanel(Portal parent, int userID) {
        this.fascade = parent.getFascade();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Page Title
        JLabel titleLabel = new JLabel("Add & Edit Your Records");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));

        add(titleLabel);

        // Check status of Abstract
        // If NULL, add option only
        // If Not Null, update / delete only
        String abstractString = fascade.getFacultyAbstract(userID);
        if (abstractString == null) {
            // Add Option
            JLabel addAbstractLabel = new JLabel("Add/Update/Delete Abstract:");
            addAbstractLabel.setFont(addAbstractLabel.getFont().deriveFont(Font.BOLD, 14f));
            addAbstractLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addAbstractLabel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    32));

            add(addAbstractLabel);

            abstractText = new JLabel();
            abstractText.setFont(abstractText.getFont().deriveFont(Font.PLAIN, 14f));
            abstractText.setAlignmentX(Component.LEFT_ALIGNMENT);
            abstractText.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    32));

            add(abstractText);

            JPanel addPanel = new JPanel();
            addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
            addPanel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    64));
            addPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JTextField addArea = new JTextField();
            addArea.setBackground(Color.decode("#272A2D"));
            addArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            addArea.setPreferredSize(new Dimension(0, 32));
            addArea.setMinimumSize(new Dimension(0, 32));
            addArea.putClientProperty(FlatClientProperties.STYLE, "arc:12;");

            JButton addButton = new JButton(
                    new ImageIcon(getClass().getResource("/frontend/assets/images/plus.png")));
            addButton.setBackground(Color.decode("#272A2D"));
            addButton.setPreferredSize(new Dimension(32, 32));
            addButton.setMaximumSize(new Dimension(32, 32));
            addButton.setMinimumSize(new Dimension(32, 32));
            addButton.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = addArea.getText();
                    boolean result = fascade.AddFacultyAbstract(userID, text);
                    if (!result) {
                        JOptionPane.showMessageDialog(null, "There was an error adding the abstract.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    updateAbstractState(text);
                    JOptionPane.showMessageDialog(null, "Abstract added successfully.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            addPanel.add(addArea);
            addPanel.add(addButton);

            add(addPanel);
        } else {
            // Show Abstract
            JLabel abstractLabel = new JLabel("Abstract:");
            abstractLabel.setFont(abstractLabel.getFont().deriveFont(Font.BOLD, 14f));
            abstractLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            abstractLabel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    32));

            add(abstractLabel);
            abstractText = new JLabel(abstractString);
            abstractText.setFont(abstractText.getFont().deriveFont(Font.PLAIN, 14f));
            abstractText.setAlignmentX(Component.LEFT_ALIGNMENT);
            abstractText.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    32));

            add(abstractText);
            // Update and Remove Option

            // Update
            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.X_AXIS));
            updatePanel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    64));
            updatePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel updateAbstractLabel = new JLabel("Add/Update/Delete Abstract:");
            updateAbstractLabel.setFont(updateAbstractLabel.getFont().deriveFont(Font.BOLD, 14f));
            updateAbstractLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            updateAbstractLabel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE,
                    32));

            add(updateAbstractLabel);

            JTextField updateArea = new JTextField();
            updateArea.setBackground(Color.decode("#272A2D"));
            updateArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            updateArea.setPreferredSize(new Dimension(0, 32));
            updateArea.setMinimumSize(new Dimension(0, 32));
            updateArea.putClientProperty(FlatClientProperties.STYLE, "arc:12;");

            JButton updateButton = new JButton(
                    new ImageIcon(getClass().getResource("/frontend/assets/images/plus.png")));
            updateButton.setBackground(Color.decode("#272A2D"));
            updateButton.setPreferredSize(new Dimension(32, 32));
            updateButton.setMaximumSize(new Dimension(32, 32));
            updateButton.setMinimumSize(new Dimension(32, 32));
            updateButton.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = updateArea.getText();
                    boolean result = fascade.UpdateFacultyAbstract(userID, text);
                    if (!result) {
                        JOptionPane.showMessageDialog(null, "There was an error updating the abstract.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    updateAbstractState(text);
                    JOptionPane.showMessageDialog(null, "Abstract updated successfully.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            updatePanel.add(updateArea);
            updatePanel.add(updateButton);

            add(updatePanel);

            // Remove
            JButton removeButton = new JButton(
                    "Remove Abstract");
            removeButton.setBackground(Color.decode("#272A2D"));
            // removeButton.setPreferredSize(new Dimension(32, 32));
            // removeButton.setMaximumSize(new Dimension(32, 32));
            // removeButton.setMinimumSize(new Dimension(32, 32));
            removeButton.putClientProperty(FlatClientProperties.STYLE, "arc:12;");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean result = fascade.RemoveFacultyAbstract(userID);
                    if (!result) {
                        JOptionPane.showMessageDialog(null, "There was an error removing the abstract.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    updateAbstractState("No Abstract Found.");
                    JOptionPane.showMessageDialog(null, "Abstract removed successfully.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            add(removeButton);
        }
    }

    private void updateAbstractState(String abstractString) {
        abstractText.setText(abstractString);
        abstractText.revalidate();
        abstractText.repaint();
    }
}
