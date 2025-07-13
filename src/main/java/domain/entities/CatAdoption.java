package domain.entities;

import java.time.LocalDate;

public class CatAdoption extends Adoption {
    public CatAdoption(int id, Adopter adopter, Employee employee, Pet pet, LocalDate dateAdoption) {
        super(id, adopter, employee, pet, dateAdoption);
    }

    public CatAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Checking if a litter box should be delivered...");
    }
}
