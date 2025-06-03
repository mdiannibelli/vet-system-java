package domain.entities;

import domain.factories.AdoptionFactory;
import domain.interfaces.EmployeeManagement;

import java.util.Date;

public class Employee extends Person implements EmployeeManagement {
    private static Employee employeeInstance;
    private final String charge;

    private Employee(String name, int age, Date birthDate, String address, String charge) {
        super(name, age, birthDate, address);
        this.charge = charge;
    }

    public static Employee create(String name, int age, String address, Date birthDate, String charge) {
        if(employeeInstance == null) {
            employeeInstance = new Employee(name, age, birthDate, address, charge);
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
        return AdoptionFactory.create(adopter, employeeInstance , pet);
    }
}
