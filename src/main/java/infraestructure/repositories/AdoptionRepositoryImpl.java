package infraestructure.repositories;

import domain.entities.Adoption;
import domain.repositories.AdoptionRepository;

import java.util.ArrayList;
import java.util.List;

public class AdoptionRepositoryImpl implements AdoptionRepository {
    private static final List<Adoption> adoptions = new ArrayList<Adoption>();

    public void save(Adoption adoption) {
        adoptions.add(adoption);
    }

    public List<Adoption> getAll() {
        return new ArrayList<>(adoptions);
    }
}
