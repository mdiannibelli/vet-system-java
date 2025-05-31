package domain.entities;

import domain.interfaces.EmployeeManagement;

import java.util.Date;

public class Employee extends Person implements EmployeeManagement {
    private static Employee employeeInstance;
    private final String charge;

    private Employee(String name, int age, String address, Date birthDate, String charge) {
        super(name, age, address, birthDate);
        this.charge = charge;
    }

    public static Employee create(String name, int age, String address, Date birthDate, String charge) {
        if(employeeInstance == null) {
            employeeInstance = new Employee(name, age, address, birthDate, charge);
        };
        return employeeInstance;
    }

    public static Employee getInstance() {
        return employeeInstance;
    }

    public String getCharge() {
        return charge;
    }


    @Override
    public Adoption registerAdoption(Adopter adopter, Pet pet) {
        return new Adoption(adopter, employeeInstance , pet);
    }
}
