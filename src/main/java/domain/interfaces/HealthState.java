package domain.interfaces;

import domain.entities.Pet;

import java.util.List;

public interface HealthState {
    List<String> getCareInstructions();
    boolean wantsToPlay();
    void onTemperatureChange(Pet pet);
}
