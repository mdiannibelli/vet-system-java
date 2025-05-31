package domain.entities;

import java.time.LocalDate;

public class Adoption {
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

    public Ticket generateTicket() {
        return new Ticket(this);
    }


}
