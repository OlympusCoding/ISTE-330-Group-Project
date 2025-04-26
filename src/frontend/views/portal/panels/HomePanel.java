package frontend.views.portal.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.*;

import backend.GUIFascade;
import frontend.components.Searchbar;
import frontend.components.Stats;
import frontend.views.Register;
import frontend.views.portal.Portal;
import types.StatsType;
import types.enums.UserType;
import types.user.userTypes.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePanel extends JPanel {

    public HomePanel(Portal parent, int userID) {
        GUIFascade fascade = parent.getFascade();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Get User Type
        UserType userType = fascade.GetUserType(userID);

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

        // Search label
        String searchLabelTest = "";
        if (userType.equals(UserType.faculty)) {
            searchLabelTest = "Search for student by abstract:";
        } else if (userType.equals(UserType.student)) {
            searchLabelTest = "Search for faculty by interests:";
        } else {
            searchLabelTest = "Search for student or faculty by interest:";
        }

        JLabel searchLabel = new JLabel(searchLabelTest);
        searchLabel.setBorder(new EmptyBorder(16, 8, 2, 8));
        searchLabel.setFont(searchLabel.getFont().deriveFont(Font.BOLD, 14f));
        searchLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchLabel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                32));

        add(searchLabel);

        // Search bar
        Searchbar searchbar = new Searchbar();

        // Get search field and button
        JTextField searchField = searchbar.getSearchField();
        JButton searchButton = searchbar.getSearchButton();

        // Results panel
        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(new EmptyBorder(2, 8, 8, 8));
        resultsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultsPanel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                Integer.MAX_VALUE));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                if (searchText.isEmpty() || searchText.equals("Search")) {
                    JOptionPane.showMessageDialog(parent, "Please enter a search term.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Clear the search field
                searchField.setText("Search");

                if (userType.equals(UserType.faculty)) {
                    // Search for students by abstract
                    JOptionPane.showMessageDialog(parent, "No students found with the given abstract.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (userType.equals(UserType.student)) {
                    // Search for faculty by interests
                    String[] interests = searchText.split(",");
                    List<String> interestList = List.of(interests);
                    List<Faculty> faculty = fascade.SearchForFacultyByInterest(interestList);
                    if (faculty.isEmpty()) {
                        JOptionPane.showMessageDialog(parent, "No faculty found with the given interests.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    resultsPanel.removeAll();
                    for (Faculty fac : faculty) {
                        JLabel facultyLabel = new JLabel(fac.getFirstName() + " " + fac.getLastName());
                        facultyLabel.setFont(facultyLabel.getFont().deriveFont(Font.PLAIN, 14f));
                        facultyLabel.setForeground(Color.WHITE);
                        facultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        facultyLabel.setBorder(new EmptyBorder(4, 8, 4, 8));
                        resultsPanel.add(facultyLabel);
                    }
                    resultsPanel.revalidate();
                    resultsPanel.repaint();

                } else {
                    // Search for students or faculty by interest
                    JOptionPane.showMessageDialog(parent, "No students or faculty found with the given intrest.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });

        add(searchbar);
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBorder(new EmptyBorder(0, 8, 8, 8));
        add(scrollPane);

    }
}