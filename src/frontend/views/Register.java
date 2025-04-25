package frontend.views;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

import backend.GUIFascade;

public class Register extends JFrame implements ActionListener {
    private GUIFascade fascade;

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> userTypeField;
    private JButton registerButton;

    public Register(GUIFascade fascade) {
        super("Register");
        this.fascade = fascade;
        setBackground(Color.decode("#202225"));
        setSize(300, 425);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        /* Username Field */
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setMinimumSize(new Dimension(300, 32));
        usernamePanel.setMaximumSize(new Dimension(300, 32));
        usernamePanel.setBackground(Color.decode("#202225"));
        TitledBorder usernameBorder = BorderFactory.createTitledBorder("Username");
        usernameBorder.setTitleColor(Color.WHITE);
        usernameBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        usernamePanel.setBorder(usernameBorder);
        usernameField = new JTextField();
        usernameField.setBackground(Color.decode("#202225"));
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        /* Email Field */
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setMinimumSize(new Dimension(300, 32));
        emailPanel.setMaximumSize(new Dimension(300, 32));
        emailPanel.setBackground(Color.decode("#202225"));
        TitledBorder emailBorder = BorderFactory.createTitledBorder("Email");
        emailBorder.setTitleColor(Color.WHITE);
        emailBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        emailPanel.setBorder(emailBorder);
        emailField = new JTextField();
        emailField.setBackground(Color.decode("#202225"));
        emailPanel.add(emailField, BorderLayout.CENTER);

        /* Password Field */
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setMinimumSize(new Dimension(300, 32));
        passwordPanel.setMaximumSize(new Dimension(300, 32));
        passwordPanel.setBackground(Color.decode("#202225"));
        TitledBorder passwordBorder = BorderFactory.createTitledBorder("Password");
        passwordBorder.setTitleColor(Color.WHITE);
        passwordBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        passwordPanel.setBorder(passwordBorder);
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.decode("#202225"));
        passwordField.setEchoChar('*');
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        /* Confirm Password Field */
        JPanel confirmPasswordPanel = new JPanel(new BorderLayout());
        confirmPasswordPanel.setMinimumSize(new Dimension(300, 32));
        confirmPasswordPanel.setMaximumSize(new Dimension(300, 32));
        confirmPasswordPanel.setBackground(Color.decode("#202225"));
        TitledBorder confirmPasswordBorder = BorderFactory.createTitledBorder("Confirm Password");
        confirmPasswordBorder.setTitleColor(Color.WHITE);
        confirmPasswordBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        confirmPasswordPanel.setBorder(confirmPasswordBorder);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBackground(Color.decode("#202225"));
        confirmPasswordField.setEchoChar('*');
        confirmPasswordPanel.add(confirmPasswordField, BorderLayout.CENTER);

        /* User Type Field */
        String[] options = { "Faculty", "Student", "Community" };

        JPanel userTypePanel = new JPanel(new BorderLayout());
        userTypePanel.setMinimumSize(new Dimension(300, 32));
        userTypePanel.setMaximumSize(new Dimension(300, 32));
        userTypePanel.setBackground(Color.decode("#202225"));
        TitledBorder userTypeBorder = BorderFactory.createTitledBorder("Account Type");
        userTypeBorder.setTitleColor(Color.WHITE);
        userTypeBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        userTypePanel.setBorder(userTypeBorder);
        userTypeField = new JComboBox<>(options);
        userTypeField.setBackground(Color.decode("#202225"));
        userTypePanel.add(userTypeField, BorderLayout.CENTER);

        /* Register Button */
        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.setMinimumSize(new Dimension(300, 48));
        registerPanel.setMaximumSize(new Dimension(300, 48));
        registerButton = new JButton("Register");
        registerButton.setFont(registerButton.getFont().deriveFont(Font.BOLD, 14f));
        registerButton.setBackground(Color.decode("#6682FF"));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        registerPanel.add(registerButton, BorderLayout.CENTER);

        /* Login Instead */
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Color.decode("#202225"));
        JButton loginButton = new JButton("Already have an account? Login here.");
        loginButton.setFont(loginButton.getFont().deriveFont(Font.PLAIN, 13f));
        loginButton.setBackground(Color.decode("#202225"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginPanel.add(loginButton, BorderLayout.SOUTH);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(fascade);
                login.setVisible(true);
                dispose();
            }
        });

        panel.add(usernamePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(emailPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(passwordPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(confirmPasswordPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(userTypePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(registerPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(loginPanel);

        add(panel);
        setVisible(true);
    }

    public void dispose() {
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
