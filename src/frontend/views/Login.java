package frontend.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        super("Login");
        setSize(250, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        /* Username Field */
        JPanel usernamePanel = new JPanel(new BorderLayout());
        usernamePanel.setBorder(BorderFactory.createTitledBorder("Username"));
        usernameField = new JTextField();
        usernamePanel.add(usernameField, BorderLayout.CENTER);

        /* Password Field */
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBorder(BorderFactory.createTitledBorder("Password"));
        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        /* Login Button */
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
        }
    }
}
