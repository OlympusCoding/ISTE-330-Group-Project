package frontend.views.portal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.GUIFascade;
import frontend.components.Sidebar;
import frontend.views.portal.panels.AddPanel;
import frontend.views.portal.panels.CreditsPanel;
import frontend.views.portal.panels.HomePanel;
import types.enums.UserType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Portal extends JFrame {
    private GUIFascade fascade;

    public Portal(GUIFascade fascade, int userID, UserType userType) {
        super();

        this.fascade = fascade;

        if (userType == UserType.student) {
            setTitle("Student - Portal");
        } else if (userType == UserType.faculty) {
            setTitle("Faculty - Portal");
        } else if (userType == UserType.community) {
            setTitle("Community - Portal");
        } else {
            setTitle("Portal");
        }

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new CardLayout());
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        Map<String, JPanel> panels = new HashMap<>();

        JPanel home = new HomePanel(this, userID);
        panels.put("home", home);
        mainPanel.add(home, "home");

        JPanel add = new AddPanel(this, userID);
        panels.put("add", add);
        mainPanel.add(add, "add");

        JPanel credits = new CreditsPanel();
        panels.put("credits", credits);
        mainPanel.add(credits, "credits");

        add(mainPanel, BorderLayout.CENTER);

        Sidebar sidebar = new Sidebar(this, panels, userType);
        add(sidebar, BorderLayout.WEST);

        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "home");
    }

    public GUIFascade getFascade() {
        return fascade;
    }
}
