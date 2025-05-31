package domain.entities;

import java.util.Date;

public class Adopter extends Person {
    Pet adoptedPet;
    Adoption adoption;

    public Adopter(String name, int age, String address, Date birthdate) {
        super(name, age, address, birthdate);
        this.adoptedPet = null;
        this.adoption = null;
    }

    public Pet getAdoptedPet() {
        return adoptedPet;
    }

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoptedPet(Pet adoptedPet) {
        this.adoptedPet = adoptedPet;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }
}
