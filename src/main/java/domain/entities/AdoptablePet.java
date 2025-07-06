package domain.entities;

import domain.interfaces.HealthState;
import enums.Species;

import java.util.Date;

public class AdoptablePet extends Pet {
    public AdoptablePet(String name, Date birthDate, double weight, double temperature, Species specie, HealthState state) {
        super(name, birthDate, weight, temperature, specie, state);
    }
}
