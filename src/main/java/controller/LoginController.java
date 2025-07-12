package controller;

import dao.EmployeeDAO;
import domain.entities.Employee;
import infraestructure.daos.EmployeeIMPL;
import views.EmployeeRegisterView;
import views.LoginView;
import views.MainMenuView;

import javax.swing.*;

public class LoginController {
    private final EmployeeDAO employeeDAO;
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        try {
            this.employeeDAO = new EmployeeIMPL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
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

    public void openRegister() {
        loginView.dispose();
        new RegisterEmployeeController(new EmployeeRegisterView());
    }
}
