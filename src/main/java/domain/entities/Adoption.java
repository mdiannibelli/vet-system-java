package domain.entities;

import java.time.LocalDate;

public abstract class Adoption {
    protected Adopter adopter;
    protected Employee employee;
    protected Pet pet;
    protected LocalDate dateAdoption;


    public Adoption(Adopter adopter, Employee employee, Pet pet) {
        this.adopter = adopter;
        this.employee = employee;
        this.pet = pet;
        this.dateAdoption = LocalDate.now();
    }

    public final void processAdoption() {
        registerAdopterData();
        registerEmployee();
        savePetData();
        specifiedSteps();
        generateTicket();
    }

    private void registerAdopterData() {
        System.out.println("Adopter: " + adopter.getName());
    }

    private void registerEmployee() {
        System.out.println("Employee: " + employee.getName());
    }

    private void savePetData() {
        System.out.println("Pet registered: " + pet.getName());
    }

    public Ticket generateTicket() {
        return new Ticket(this);
    }

    protected abstract void specifiedSteps();
}
