package frontend.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import backend.GUIFascade;
import frontend.views.portal.Portal;
import types.enums.UserType;

import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    private GUIFascade fascade;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login(GUIFascade fascade) {
        super("Login");
        this.fascade = fascade;
        setBackground(Color.decode("#202225"));
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        panel.setBackground(Color.decode("#202225"));

        /* Username Field */
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setMinimumSize(new Dimension(250, 32));
        usernamePanel.setMaximumSize(new Dimension(250, 32));
        usernamePanel.setBackground(Color.decode("#202225"));
        TitledBorder usernameBorder = BorderFactory.createTitledBorder("Username");
        usernameBorder.setTitleColor(Color.WHITE);
        usernameBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        usernamePanel.setBorder(usernameBorder);
        usernameField = new JTextField();
        usernameField.setBackground(Color.decode("#202225"));
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        /* Password Field */
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setMinimumSize(new Dimension(250, 32));
        passwordPanel.setMaximumSize(new Dimension(250, 32));
        passwordPanel.setBackground(Color.decode("#202225"));
        TitledBorder passwordBorder = BorderFactory.createTitledBorder("Password");
        passwordBorder.setTitleColor(Color.WHITE);
        passwordBorder.setBorder(BorderFactory.createLineBorder(Color.decode("#202225")));
        passwordPanel.setBorder(passwordBorder);
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.decode("#202225"));
        passwordField.setEchoChar('*');
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        /* Login Button */
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginButton = new JButton("Login");
        loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD, 14f));
        loginButton.setBackground(Color.decode("#6682FF"));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        loginPanel.setBorder(new EmptyBorder(8, 0, 0, 0));
        loginPanel.add(loginButton, BorderLayout.CENTER);

        getRootPane().setDefaultButton(loginButton);

        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(loginPanel);

        add(panel);
        setVisible(true);
    }

    public void dispose() {
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Check if username and password are not empty
            if (!username.isEmpty() && !password.isEmpty()) {
                // Attempt to login
                if (!fascade.Login(username, password)) {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get userID and userType
                int userID = fascade.GetUserID(username);

                if (userID == -1) {
                    JOptionPane.showMessageDialog(this, "User ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UserType userType = fascade.GetUserType(userID);

                // If login is successful, close the login window and open the portal
                this.setVisible(false);
                this.dispose();
                Portal portal = new Portal(fascade, userID, userType);
                portal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
