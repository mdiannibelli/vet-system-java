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
    void listPetsWithSpecialCare();
    void listAdopterNames();
    void countPetsPerSpecie();
    void showPetsOrderByName();
    void findPetsWithMinimumWeight(double weight);
    void concatPetsNames();
    void averageAdopterAges();
    void filterRecentAdoptions();
    void transformPet();
    void verifyAdoptionWithoutLinkedPet();
}
