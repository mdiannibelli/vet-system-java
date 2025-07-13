package domain.entities;

import domain.interfaces.HealthState;
import enums.Species;

import java.util.Date;

public abstract class Pet {
    private int id;
    protected String name;
    protected Date birthDate;
    protected double weight;
    protected double temperature;
    protected Species specie;
    protected HealthState state;

    public Pet(String name, Date birthDate, double weight, double temperature, Species specie, HealthState state) {
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.temperature = temperature;
        this.specie = specie;
        this.state = state;
    }

    public Species getSpecie() {
        return this.specie;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public double getWeight() {
        return this.weight;
    }

    public HealthState getState() {
        return this.state;
    }

    public void setState(HealthState state) {
        this.state = state;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double newTemp) {
        this.temperature = newTemp;
        this.state.onTemperatureChange(this);
    }
}
