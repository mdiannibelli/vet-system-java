package domain.entities;

import domain.factories.AdoptionFactory;
import domain.interfaces.EmployeeManagement;

import java.util.Date;

public class Employee extends Person implements EmployeeManagement {
    private int id;
    private static Employee employeeInstance;
    private final String charge;
    private String username;
    private String password;

    public Employee(String name, String username, String password, int age, Date birthDate, String address,
            String charge) {
        super(name, age, birthDate, address);
        this.username = username;
        this.password = password;
        this.charge = charge;
    }

    public static Employee create(String name, String username, String password, int age, String address,
            Date birthDate, String charge) {
        if (employeeInstance == null) {
            employeeInstance = new Employee(name, username, password, age, birthDate, address, charge);
        }
        return employeeInstance;
    }

    public static Employee createNewEmployee(String name, String username, String password, int age, String address,
            Date birthDate, String charge) {
        return new Employee(name, username, password, age, birthDate, address, charge);
    }

    public static Employee getInstance() {
        if (employeeInstance == null) {
            employeeInstance = new Employee("Default", "default", "default", 0, new Date(), "Default", "Default");
        }
        return employeeInstance;
    }

    public String getCharge() {
        return charge;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmployee(Employee employee) {
        employeeInstance = employee;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public Adoption registerAdoption(Adopter adopter, Pet pet) {
        return AdoptionFactory.create(adopter, employeeInstance, pet);
    }
}
