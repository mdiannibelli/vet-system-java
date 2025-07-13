package dao;

import domain.entities.Pet;

public interface PetDAO {
    void save(Pet pet);

    Pet findById(int id);

    Pet findByName(String name);

    void updateHealthState(int petId, String healthState);

    void createTable();
}