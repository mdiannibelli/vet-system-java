package domain.entities;

public class DogAdoption extends Adoption {
    public DogAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Checking mandatory dog vaccinations...");
    }
}
