package domain.interfaces;

import domain.entities.Adopter;
import domain.entities.Adoption;
import domain.entities.Pet;

public interface EmployeeManagement {
    public Adoption registerAdoption(Adopter adpter, Pet pet);
}
