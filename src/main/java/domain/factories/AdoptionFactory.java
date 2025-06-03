package domain.factories;

import domain.entities.*;
import enums.Species;

public class AdoptionFactory {
    public static Adoption create(Adopter adopter, Employee employee, Pet pet) {
        switch (pet.getSpecie()) {
            case DOG:
                return new DogAdoption(adopter, employee, pet);
            case  CAT:
                return new CatAdoption(adopter, employee, pet);
            case  RABBIT:
                return new RabbitAdoption(adopter, employee, pet);
            default:
                throw new IllegalArgumentException("Pet type not supported");
        }
    }
}
