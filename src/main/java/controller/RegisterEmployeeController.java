package controller;

import dao.EmployeeDAO;
import domain.entities.Employee;
import infraestructure.daos.EmployeeIMPL;
import views.EmployeeRegisterView;
import views.LoginView;
import exceptions.*;

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
            JOptionPane.showMessageDialog(null, "Error al inicializar DAO: " + e.getMessage());
            throw new DatabaseException("No se pudo inicializar el DAO de empleados", e);
        }
    }

    public void registerEmployee() {
        try {
            String name = view.txtName.getText();
            String username = view.txtUsername.getText();
            String password = new String(view.txtPassword.getPassword());

            // Validar campos obligatorios
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                throw new ValidationException("Todos los campos son obligatorios");
            }

            // Validar que el usuario no exista
            if (employeeDAO.findExistAccount(username)) {
                throw new EmployeeException("El usuario ya existe");
            }

            Employee emp = Employee.createNewEmployee(name, username, password, 30, "Generic Address", new Date(),
                    "Employee");

            employeeDAO.save(emp);
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            view.dispose();
            new LoginController(new LoginView());

        } catch (ValidationException | EmployeeException | DatabaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            throw new AdoptionException("Error inesperado al registrar empleado", e);
        }
    }
}
