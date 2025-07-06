package dao;

import domain.entities.Adoption;

public interface AdoptionDAO {
    void save(Adoption adoption);

    Adoption findById(int id);
}