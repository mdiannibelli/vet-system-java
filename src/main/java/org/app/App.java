package org.app;

import config.DatabaseConnection;
import controller.LoginController;
import dao.AdopterDAO;
import dao.AdoptionDAO;
import dao.EmployeeDAO;
import dao.PetDAO;
import domain.entities.Employee;
import infraestructure.daos.AdopterDAOImpl;
import infraestructure.daos.AdoptionDAOImpl;
import infraestructure.daos.EmployeeIMPL;
import infraestructure.daos.PetDAOImpl;
import views.LoginView;

import java.sql.Connection;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            // Create all necessary tables
            createAllTables();

            // Create test employee if it does not exist
            createTestEmployee();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        new LoginController(new LoginView());
    }

    private static void createAllTables() {
        try {
            EmployeeDAO employeeDAO = new EmployeeIMPL();
            AdopterDAO adopterDAO = new AdopterDAOImpl();
            PetDAO petDAO = new PetDAOImpl();
            AdoptionDAO adoptionDAO = new AdoptionDAOImpl();

            employeeDAO.createTable();
            adopterDAO.createTable();
            petDAO.createTable();
            adoptionDAO.createTable();

            System.out.println("All tables was created sucessfully");
        } catch (Exception e) {
            System.err.println("Error at creating tables: " + e.getMessage());
        }
    }

    private static void createTestEmployee() {
        try {
            EmployeeDAO employeeDAO = new EmployeeIMPL();

            Employee existingEmployee = employeeDAO.findByUserAndPassword("admin", "admin");
            if (existingEmployee == null) {
                // Crear empleado de prueba
                Employee testEmployee = new Employee(
                        "Administrador",
                        "admin",
                        "admin",
                        30,
                        new Date(),
                        "Dirección de Prueba",
                        "Veterinario");
                employeeDAO.save(testEmployee);
                System.out.println("Empleado de prueba creado: admin/admin");
            }
        } catch (Exception e) {
            System.err.println("Error al crear empleado de prueba: " + e.getMessage());
        }
    }
}
