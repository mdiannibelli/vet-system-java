package domain.entities;

import java.time.LocalDate;

public class DogAdoption extends Adoption {
    public DogAdoption(int id, Adopter adopter, Employee employee, Pet pet, LocalDate dateAdoption) {
        super(id, adopter, employee, pet, dateAdoption);
    }

    public DogAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Checking mandatory dog vaccinations...");
    }
}
