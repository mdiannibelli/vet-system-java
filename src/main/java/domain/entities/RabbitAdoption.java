package domain.entities;

public class RabbitAdoption extends Adoption {
    public RabbitAdoption(Adopter adopter, Employee employee, Pet pet) {
        super(adopter, employee, pet);
    }

    @Override
    protected void specifiedSteps() {
        System.out.println("Recommending care for your rabbit's cage...");
    }
}

