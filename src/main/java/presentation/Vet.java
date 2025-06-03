package presentation;

import domain.entities.*;
import domain.interfaces.VetAdmin;
import domain.repositories.AdoptionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Vet implements VetAdmin {
    private final HashSet<Pet> pets;
    private final Employee employee;
    private final AdoptionRepository adoptionRepository;

    public Vet(Employee employee, AdoptionRepository adoptionRepository) {
        this.employee = employee;
        this.adoptionRepository = adoptionRepository;
        this.pets = new HashSet<>();
    }

    @Override
    public List<Adoption> getAdoptions() {
        return adoptionRepository.getAll();
    }

    @Override
    public HashSet<Pet> getPets() {
        return pets;
    }

    @Override
    public void registerPet(Pet pet) {
        pets.add(pet);
    }

    @Override
    public void registerAdoption(Adopter adopter, Pet pet) {
        Adoption adoption = employee.registerAdoption(adopter, pet);
        adoption.processAdoption();
        adoptionRepository.save(adoption);
        adopter.setAdoption(adoption);
        adopter.setAdoptedPet(pet);
        Ticket ticket = adoption.generateTicket();
        ticket.print();
    }
}
