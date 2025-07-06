package dao;

import domain.entities.Pet;

public interface PetDAO {
    void save(Pet pet);

    Pet findById(int id);

    Pet findByName(String name);
}