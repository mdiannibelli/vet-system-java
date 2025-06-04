package domain.entities;

import domain.interfaces.HealthState;
import enums.Species;

import java.util.Date;

public class Rabbit extends Pet {

    public Rabbit(String name, Date birthDate, double weight, double temperature, Species specie, HealthState health) {
        super(name, birthDate, weight, temperature, specie, health);
    }

    public void getCareInformation() {
        for(String instruction : this.getState().getCareInstructions()) {
            System.out.println(instruction);
        }
    }
}
