package frontend.views.portal.panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.*;

import backend.GUIFascade;
import frontend.components.Searchbar;
import frontend.components.Stats;
import frontend.views.portal.Portal;
import types.StatsType;
import types.enums.UserType;
import types.user.userTypes.Faculty;
import types.user.userTypes.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePanel extends JPanel {
    private GUIFascade fascade;
    private Portal parent;

    private JButton clearButton;
    private JPanel resultsPanel;
    private JPanel resultsContainer;
    private JSeparator separator;
    private JScrollPane scrollPane;

    public HomePanel(Portal parent, int userID) {
        this.parent = parent;
        this.fascade = parent.getFascade();
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
        String searchLabelText = "";
        if (userType.equals(UserType.faculty)) {
            searchLabelText = "Search for student by abstract:";
        } else if (userType.equals(UserType.student)) {
            searchLabelText = "Search for faculty by interests:";
        } else {
            searchLabelText = "Search for student or faculty by interest:";
        }

        JLabel searchLabel = new JLabel(searchLabelText);
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

        parent.getRootPane().setDefaultButton(searchButton);

        // Results panel
        this.resultsContainer = new JPanel();
        resultsContainer.setLayout(new BoxLayout(resultsContainer, BoxLayout.Y_AXIS));
        resultsContainer.setBorder(new EmptyBorder(2, 8, 0, 8));
        resultsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultsContainer.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                235));

        this.resultsPanel = new JPanel();
        this.resultsPanel.setLayout(new BoxLayout(this.resultsPanel, BoxLayout.Y_AXIS));
        this.scrollPane = new JScrollPane(this.resultsPanel);
        this.scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.scrollPane.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                235));
        this.scrollPane.setPreferredSize(new Dimension(0, 235));

        this.clearButton = new JButton(
                new ImageIcon(getClass().getResource("/frontend/assets/images/clear.png")));
        this.clearButton.setBackground(Color.decode("#272A2D"));
        this.clearButton.setBorderPainted(false);
        this.clearButton.setFocusPainted(false);

        this.clearButton.setPreferredSize(new Dimension(32, 32));
        this.clearButton.setMaximumSize(new Dimension(32, 32));
        this.clearButton.setMinimumSize(new Dimension(32, 32));
        this.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultsPanel.removeAll();
                resultsContainer.removeAll();
                resultsContainer.revalidate();
                resultsContainer.repaint();
            }
        });

        this.separator = new JSeparator(SwingConstants.HORIZONTAL);
        this.separator.setForeground(Color.decode("#26282B"));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultsPanel.removeAll();
                resultsContainer.removeAll();
                resultsContainer.revalidate();
                resultsContainer.repaint();

                String searchText = searchField.getText().trim();
                if (searchText.isEmpty() || searchText.equals("Search")) {
                    JOptionPane.showMessageDialog(parent, "Please enter a search term.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Clear the search field
                searchField.setText("");

                // Check UserType and perform search accordingly
                if (userType.equals(UserType.faculty)) {
                    handleFacultySearch(searchText);
                } else if (userType.equals(UserType.student)) {
                    handleStudentSearch(searchText);
                } else {
                    handleCommunitySearch(searchText);
                }

            }
        });

        add(searchbar);
        add(resultsContainer);
    }

    private void handleStudentSearch(String searchText) {
        String[] interests = searchText.split(",");
        List<String> interestList = List.of(interests);
        List<Faculty> faculty = fascade.SearchForFacultyByInterest(interestList);
        if (faculty.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No faculty found with the given interests.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        createUIComponents(searchText);

        int index = 1;
        for (Faculty fac : faculty) {
            JButton facultyButton = new JButton("Faculty: " +
                    index + ": " + fac.getFirstName() + " " + fac.getLastName() + " - "
                    + fac.getBuildingNum() + " - " + fac.getOfficeNum() + " - " + fac.getEmail());
            facultyButton.setFont(facultyButton.getFont().deriveFont(Font.PLAIN, 14f));
            facultyButton.setForeground(Color.WHITE);
            facultyButton.setBackground(Color.decode("#202225"));
            facultyButton.putClientProperty(FlatClientProperties.STYLE, "arc:16;");
            facultyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            facultyButton.setBorder(new EmptyBorder(4, 8, 4, 8));
            this.resultsPanel.add(facultyButton);
            index++;
        }

        resultsContainer.add(scrollPane);
        resultsContainer.revalidate();
        resultsContainer.repaint();

    }

    private void handleFacultySearch(String searchText) {
        List<Student> students = fascade.SearchForStudentByAbstract(searchText);
        if (students == null || students.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No students found with the given abstract.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        createUIComponents(searchText);

        int index = 1;
        for (Student stu : students) {
            JButton studentButton = new JButton("Student: " +
                    index + ": " + stu.getFirstName() + " " + stu.getLastName() + " - " + stu.getEmail());
            studentButton.setFont(studentButton.getFont().deriveFont(Font.PLAIN, 14f));
            studentButton.setForeground(Color.WHITE);
            studentButton.setBackground(Color.decode("#202225"));
            studentButton.putClientProperty(FlatClientProperties.STYLE, "arc:16;");
            studentButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            studentButton.setBorder(new EmptyBorder(4, 8, 4, 8));
            this.resultsPanel.add(studentButton);
            index++;
        }

        resultsContainer.add(scrollPane);
        resultsContainer.revalidate();
        resultsContainer.repaint();
    }

    private void handleCommunitySearch(String searchText) {
        List<Student> students = fascade.SearchForStudentByAbstract(searchText);
        String[] interests = searchText.split(",");
        List<String> interestList = List.of(interests);
        List<Faculty> faculty = fascade.SearchForFacultyByInterest(interestList);

        if (students.isEmpty() && faculty.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No students and or faculty found with the given search.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        createUIComponents(searchText);

        int index = 1;
        for (Student stu : students) {
            JButton studentButton = new JButton("Student: " +
                    index + ": " + stu.getFirstName() + " " + stu.getLastName() + " - " + stu.getEmail());
            studentButton.setFont(studentButton.getFont().deriveFont(Font.PLAIN, 14f));
            studentButton.setForeground(Color.WHITE);
            studentButton.setBackground(Color.decode("#202225"));
            studentButton.putClientProperty(FlatClientProperties.STYLE, "arc:16;");
            studentButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            studentButton.setBorder(new EmptyBorder(4, 8, 4, 8));
            this.resultsPanel.add(studentButton);
            index++;
        }

        for (Faculty fac : faculty) {
            JButton facultyButton = new JButton("Faculty: " +
                    index + ": " + fac.getFirstName() + " " + fac.getLastName() + " - "
                    + fac.getBuildingNum() + " - " + fac.getOfficeNum() + " - " + fac.getEmail());
            facultyButton.setFont(facultyButton.getFont().deriveFont(Font.PLAIN, 14f));
            facultyButton.setForeground(Color.WHITE);
            facultyButton.setBackground(Color.decode("#202225"));
            facultyButton.putClientProperty(FlatClientProperties.STYLE, "arc:16;");
            facultyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            facultyButton.setBorder(new EmptyBorder(4, 8, 4, 8));
            this.resultsPanel.add(facultyButton);
            index++;
        }

        resultsContainer.add(scrollPane);
        resultsContainer.revalidate();
        resultsContainer.repaint();
    }

    private void createUIComponents(String searchText) {
        resultsPanel.removeAll();

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        headerPanel.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                64));

        JLabel resultsLabel = new JLabel("Results for search: " + searchText);
        resultsLabel.setForeground(Color.decode("#6682FF"));
        resultsLabel.setFont(resultsLabel.getFont().deriveFont(Font.BOLD, 14f));
        headerPanel.revalidate();
        headerPanel.repaint();

        headerPanel.add(resultsLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(this.clearButton);

        headerPanel.setVisible(true);
        resultsContainer.add(headerPanel);
        resultsContainer.add(Box.createVerticalStrut(8));
        resultsContainer.add(separator);
    }
}