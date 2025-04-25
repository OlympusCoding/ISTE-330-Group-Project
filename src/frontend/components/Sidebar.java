package frontend.components;

import javax.swing.*;
import javax.swing.border.Border;

import frontend.views.Login;
import frontend.views.portal.Portal;
import types.enums.UserType;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Sidebar extends JPanel {
    private NavButton homeButton;
    private NavButton addButton;
    private NavButton creditsButton;
    private NavButton logoutButton;

    private String activePanel = "home";

    Map<String, NavButton> buttons = new HashMap<>();

    public Sidebar(Portal portal, Map<String, JPanel> panels, UserType userType) {
        super();

        if (userType.equals(UserType.faculty)) {
            setLayout(new GridLayout(4, 1, 10, 10));
        } else {
            setLayout(new GridLayout(3, 1, 10, 10));
        }

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.decode("#202225"));
        Border rightBorder = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.decode("#26282B"));
        setBorder(rightBorder);

        setPreferredSize(new Dimension(64, 600));
        setMinimumSize(new Dimension(64, 600));
        setMaximumSize(new Dimension(64, 600));
        setSize(new Dimension(64, 600));

        /* Home Button */
        homeButton = new NavButton("/frontend/assets/images/home.png", "Home");
        buttons.put("home", homeButton);
        homeButton.setSelected(true);
        add(homeButton);
        /* Add Button */
        if (userType.equals(UserType.faculty)) {
            addButton = new NavButton("/frontend/assets/images/add.png", "Add");
            buttons.put("add", addButton);
            add(addButton);
        }
        /* Credits Button */
        creditsButton = new NavButton("/frontend/assets/images/credits.png", "Credits");
        buttons.put("credits", creditsButton);
        add(creditsButton);
        /* Logout Button */
        logoutButton = new NavButton("/frontend/assets/images/logout.png", "Logout");
        add(logoutButton);

        // Hide the add button if the user is not a faculty member

        /* Action Listeners */
        if (homeButton != null) {
            homeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panels.get(activePanel).setVisible(false);
                    buttons.get(activePanel).setSelected(false);
                    activePanel = "home";
                    panels.get(activePanel).setVisible(true);
                    buttons.get(activePanel).setSelected(true);
                    revalidate();
                    repaint();
                }
            });
        }

        if (addButton != null) {
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panels.get(activePanel).setVisible(false);
                    buttons.get(activePanel).setSelected(false);
                    activePanel = "add";
                    panels.get(activePanel).setVisible(true);
                    buttons.get(activePanel).setSelected(true);
                    revalidate();
                    repaint();
                }
            });
        }

        if (creditsButton != null) {
            creditsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panels.get(activePanel).setVisible(false);
                    buttons.get(activePanel).setSelected(false);
                    activePanel = "credits";
                    panels.get(activePanel).setVisible(true);
                    buttons.get(activePanel).setSelected(true);
                    revalidate();
                    repaint();
                }
            });
        }

        if (logoutButton != null) {
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        portal.setVisible(false);
                        portal.dispose();

                        Login login = new Login(portal.getFascade());
                        login.setVisible(true);
                    }
                }
            });
        }

    }
}
