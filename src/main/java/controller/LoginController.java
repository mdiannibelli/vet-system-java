package controller;

import dao.EmployeeDAO;
import domain.entities.Employee;
import infraestructure.daos.EmployeeIMPL;
import views.EmployeeRegisterView;
import views.LoginView;
import views.MainMenuView;
import exceptions.*;

import javax.swing.*;

public class LoginController {
    private final EmployeeDAO employeeDAO;
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        try {
            this.employeeDAO = new EmployeeIMPL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            throw new DatabaseException("No se pudo inicializar el DAO de empleados", e);
        }
    }

    public void login(String username, String password) {
        try {
            if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                throw new ValidationException("El usuario y contraseña son obligatorios");
            }

            Employee empleado = employeeDAO.findByUserAndPassword(username, password);
            if (empleado != null) {
                Employee.getInstance().setEmployee(empleado);
                JOptionPane.showMessageDialog(null, "Bienvenido " + empleado.getName());
                loginView.dispose();
                new MainMenuController(new MainMenuView());
            } else {
                throw new AuthenticationException("Usuario o contraseña incorrectos");
            }
        } catch (ValidationException | AuthenticationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            throw new AdoptionException("Error inesperado durante el login", e);
        }
    }

    public void openRegister() {
        loginView.dispose();
        new RegisterEmployeeController(new EmployeeRegisterView());
    }
}
