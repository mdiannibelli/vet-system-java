package domain.entities;

import java.util.Date;

public class Adopter extends Person {
    Pet adoptedPet;
    Adoption adoption;

    public Adopter(String name, int age, Date birthDate, String address) {
        super(name, age, birthDate, address );
        this.adoptedPet = null;
        this.adoption = null;
    }

    public Adopter(String name, int age, Date birthDate) {
        super(name, age, birthDate, null );
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
