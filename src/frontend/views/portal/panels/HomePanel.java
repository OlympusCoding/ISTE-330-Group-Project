package frontend.views.portal.panels;

import javax.swing.*;
import java.awt.*;

import backend.GUIFascade;
import frontend.components.Stats;
import frontend.views.portal.Portal;
import types.StatsType;

import java.util.HashMap;
import java.util.Map;

public class HomePanel extends JPanel {

    public HomePanel(Portal parent, int userID) {
        GUIFascade fascade = parent.getFascade();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + fascade.GetUserEmail(userID) + "!");
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 18f));
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        welcomeLabel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));

        add(welcomeLabel);

        // Stats
        StatsType statsData = fascade.GetStats();
        Map<String, String> statsMap = new HashMap<>();
        statsMap.put("Faculty", statsData.getFaculty());
        statsMap.put("Students", statsData.getStudents());
        statsMap.put("Community", statsData.getCommunity());
        Stats stats = new Stats(statsMap);
        add(stats);

    }
}

// setFont(welcomeLabel.getFont().deriveFont(Font.BOLD, 18f));