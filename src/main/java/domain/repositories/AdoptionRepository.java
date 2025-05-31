package domain.repositories;

import domain.entities.Adoption;

import java.util.ArrayList;
import java.util.List;

public interface AdoptionRepository {
    static final List<Adoption> adoptions = new ArrayList<Adoption>();

    public void save(Adoption adoption);

    public List<Adoption> getAll();
}
