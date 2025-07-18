package domain.entities;

import java.time.LocalDate;

public class RabbitAdoption extends Adoption {
    public RabbitAdoption(int id, Adopter adopter, Employee employee, Pet pet, LocalDate dateAdoption) {
        super(id, adopter, employee, pet, dateAdoption);
    }

    public RabbitAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Recommending care for your rabbit's cage...");
    }
}
