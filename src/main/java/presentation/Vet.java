package presentation;

import domain.entities.*;
import domain.entities.healthstates.InObservation;
import domain.exceptions.AdoptionException;
import domain.interfaces.VetAdmin;
import domain.repositories.AdoptionRepository;
import enums.Species;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

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
        try {
            if (!this.pets.contains(pet)) {
                throw AdoptionException.PetNotAvailable("Pet is not available in the vet");
            }

            if (adopter.getAge() < 18) {
                throw AdoptionException.InvalidCredentials("Adopter must be of legal age");
            }

            if (adopter.getAddress() == null) {
                throw AdoptionException.InvalidCredentials("Adopter must have an address");
            }

            Adoption adoption = employee.registerAdoption(adopter, pet);
            adoptionRepository.save(adoption);
            adopter.setAdoption(adoption);
            adopter.setAdoptedPet(pet);
            adoption.processAdoption();

        } catch (AdoptionException e) {
            System.out.println("Error in adoption process: " + e.getMessage());
        }
    }

    @Override
    public void listPetsWithSpecialCare() {
        HashSet<Pet> list = this.pets.stream().filter(pet -> !pet.getState().wantsToPlay())
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("----------------------");
        System.out.println("Care information pets");
        System.out.println("----------------------");
        list.forEach(pet -> System.out.println(pet.getName() + "\n"));
    }

    @Override
    public void listAdopterNames() {
        List<String> adopterNames = this.getAdoptions().stream().map(adoption -> adoption.getAdopter().getName()).collect(Collectors.toList());
        System.out.println("-----------------------");
        System.out.println("Adopter names");
        System.out.println("-----------------------");
        adopterNames.forEach(adopterName -> System.out.println(adopterName));
    }

    @Override
    public void countPetsPerSpecie() {
        int dogs = 0, cats = 0, rabbits = 0;
        for(Pet pet : this.getPets()) {
            if(pet.getSpecie().equals(Species.DOG)) {
                dogs++;
            }
            if(pet.getSpecie().equals(Species.CAT)) {
                cats++;
            }
            if(pet.getSpecie().equals(Species.RABBIT)) {
                rabbits++;
            }
        }

        System.out.println("-----------------------");
        System.out.println("Dogs: " + dogs);
        System.out.println("Cats: " + cats);
        System.out.println("Rabbits: " + rabbits);
        System.out.println("-----------------------");
    }

    @Override
    public void showPetsOrderByName() {
        List<Pet> pets = this.getPets().stream()
                .sorted(Comparator.comparing(Pet::getName))
                .collect(Collectors.toList());

        System.out.println("-----------------------");
        System.out.println("Pets order by pet name");
        pets.forEach(pet -> System.out.println(pet.getName()));
        System.out.println("-----------------------");
    }

    @Override
    public void findPetsWithMinimumWeight(double weight) {
        HashSet<Pet> pets = this.getPets().stream().filter(pet -> pet.getWeight() > weight).collect(Collectors.toCollection(HashSet::new));
        System.out.println("-----------------------");
        System.out.println("Pets with minimum weight");
        pets.forEach(pet -> System.out.println(pet.getName()));
        System.out.println("-----------------------");
    }

    @Override
    public void concatPetsNames() {
        String concatPetNames = this.getPets().stream().map(Pet::getName).collect(Collectors.joining(", "));
        System.out.println("-----------------------");
        System.out.println("Concat pet names: " + concatPetNames);
        System.out.println("-----------------------");
    }

    @Override
    public void averageAdopterAges() {
        Set<Adopter> adopters = this.getAdoptions().stream().map(adoption -> adoption.getAdopter()).collect(Collectors.toSet());
        List<Integer> ages = adopters.stream().map(Adopter::getAge).collect(Collectors.toList());
        int total = ages.stream().reduce(0, (prev, currentValue) -> prev + currentValue);
        double average = total/ages.size();
        System.out.println("-----------------------");
        System.out.println("Adopter age average: " + average);
        System.out.println("-----------------------");
    }

    @Override
    public void filterRecentAdoptions() {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        HashSet<Adoption> recentAdoptions = this.getAdoptions().stream()
                .filter(adoption -> adoption.getDateAdoption().isAfter(thirtyDaysAgo))
                        .collect(Collectors.toCollection(HashSet::new));
        System.out.println("-----------------------");
        System.out.println("Adoptions in the last 30 days: ");
        recentAdoptions.forEach(adoption -> System.out.println(adoption.getAdopter().getName() + " adopted a pet in " + adoption.getDateAdoption()));
        System.out.println("-----------------------");
    }

    @Override
    public void transformPet() {
        List<PetSummary> summaries = this.pets.stream()
                .map(pet -> new PetSummary(pet.getName(), pet.getSpecie().name()))
                .collect(Collectors.toList());

        summaries.forEach(System.out::println);
    }

    @Override
    public void verifyAdoptionWithoutLinkedPet() {
        HashSet<Adoption> adoptions = this.getAdoptions().stream()
                .filter(adop -> adop.getPet() == null)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("-----------------------");
        System.out.println("Adopters without linked pets");
        adoptions.forEach(adop -> System.out.println(adop.getAdopter().getName()));
        System.out.println("-----------------------");
    }
}
