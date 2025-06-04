package domain.entities;

public class PetSummary {
    private final String name;
    private final String species;

    public PetSummary(String name, String species) {
        this.name = name;
        this.species = species;
    }

    @Override
    public String toString() {
        return name + " (" + species + ")";
    }
}