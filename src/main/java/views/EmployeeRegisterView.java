package views;

import controller.RegisterEmployeeController;
import javax.swing.*;
import java.awt.*;

public class EmployeeRegisterView extends JFrame {
    public JTextField txtUsername, txtName;
    public JPasswordField txtPassword;
    public JButton registerBtn;

    public EmployeeRegisterView() {
        this.createView();

        RegisterEmployeeController registerController = new RegisterEmployeeController(this);

        this.registerBtn.addActionListener(e -> registerController.registerEmployee());
    }

    private void createView() {
        setTitle("Employee register");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        txtName = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        registerBtn = new JButton("Register");

        add(new JLabel("Name:"));
        add(txtName);
        add(new JLabel("Username:"));
        add(txtUsername);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(registerBtn);

        setVisible(true);
    }
}
