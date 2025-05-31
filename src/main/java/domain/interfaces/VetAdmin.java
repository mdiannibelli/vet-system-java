package domain.interfaces;

import domain.entities.Adopter;
import domain.entities.Adoption;
import domain.entities.Pet;

import java.util.HashSet;
import java.util.List;

public interface VetAdmin {
    List<Adoption> getAdoptions();
    HashSet<Pet> getPets();
    void registerPet(Pet pet);
    void registerAdoption(Adopter adopter, Pet pet);
}
