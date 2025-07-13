package dao;

import java.util.List;

import domain.entities.Adoption;

public interface AdoptionDAO {
    void save(Adoption adoption);

    List<Adoption> findAll();

    boolean deleteAdoption(int id);

    Adoption findById(int id);

    void createTable();
}