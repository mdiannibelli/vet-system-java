package dao;

import domain.entities.Adopter;

public interface AdopterDAO {
    void save(Adopter adopter);

    Adopter findById(int id);

    Adopter findByName(String name);
}