package domain.entities;

public class CatAdoption extends Adoption {
    public CatAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Checking if a litter box should be delivered...");
    }
}
