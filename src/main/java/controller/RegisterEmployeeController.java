package controller;

import dao.EmployeeDAO;
import domain.entities.Employee;
import infraestructure.daos.EmployeeIMPL;
import views.EmployeeRegisterView;
import views.LoginView;

import javax.swing.*;
import java.util.Date;

public class RegisterEmployeeController {
    private final EmployeeRegisterView view;
    private final EmployeeDAO employeeDAO;

    public RegisterEmployeeController(EmployeeRegisterView view) {
        this.view = view;

        try {
            this.employeeDAO = new EmployeeIMPL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at DAO initialization: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void registerEmployee() {
        try {
            String name = view.txtName.getText();
            String username = view.txtUsername.getText();
            String password = new String(view.txtPassword.getPassword());

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required.");
                return;
            }

            if (employeeDAO.findExistAccount(username)) {
                JOptionPane.showMessageDialog(null, "User already exists.");
                return;
            }

            Employee emp = Employee.createNewEmployee(name, username, password, 30, "Generic Address", new Date(),
                    "Employee");

            employeeDAO.save(emp);
            JOptionPane.showMessageDialog(null, "Register successful");
            view.dispose();
            new LoginController(new LoginView());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error registering employee: " + e.getMessage());
        }
    }
}
