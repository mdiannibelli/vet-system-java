package controller;

import dao.EmployeeDAO;
import domain.entities.Employee;
import infraestructure.daos.EmployeeIMPL;
import views.EmployeeRegisterView;
import views.LoginView;
import views.MainMenuView;

import javax.swing.*;

public class LoginController {
    private final LoginView loginView;
    private final EmployeeDAO employeeDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        try {
            this.employeeDAO = new EmployeeIMPL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at database: " + e.getMessage());
            throw new RuntimeException(e);
        }

        // Listeners
        loginView.loginBtn.addActionListener(e -> login());
        loginView.registerBtn.addActionListener(e -> openRegister());
    }

    private void login() {
        String username = loginView.txtUsername.getText();
        String password = new String(loginView.txtPassword.getPassword());

        Employee empleado = employeeDAO.findByUserAndPassword(username, password);
        if (empleado != null) {
            Employee.getInstance().setEmployee(empleado);
            JOptionPane.showMessageDialog(null, "Welcome " + empleado.getName());
            loginView.dispose();
            new MainMenuController(new MainMenuView());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.");
        }
    }

    private void openRegister() {
        loginView.dispose();
        new RegisterEmployeeController(new EmployeeRegisterView());
    }
}
