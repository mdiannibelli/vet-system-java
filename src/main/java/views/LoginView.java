package views;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton loginBtn, registerBtn;

    public LoginView() {
        setTitle("Login - Vet");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Sign Up");

        add(new JLabel("Username:"));
        add(txtUsername);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(loginBtn);
        add(registerBtn);

        setVisible(true);
    }

}
