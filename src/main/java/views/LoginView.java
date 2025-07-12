package views;

import javax.swing.*;

import controller.LoginController;

import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton loginBtn, registerBtn;

    public LoginView() {
        this.createView();

        LoginController loginController = new LoginController(this);

        this.loginBtn.addActionListener(e -> {
            String username = this.txtUsername.getText();
            String password = new String(this.txtPassword.getPassword());
            loginController.login(username, password);
        });
        this.registerBtn.addActionListener(e -> loginController.openRegister());
    }

    private void createView() {
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
