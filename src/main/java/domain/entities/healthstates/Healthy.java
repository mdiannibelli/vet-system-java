package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;

import java.util.List;

public class Healthy implements HealthState {

    @Override
    public List<String> getCareInstructions() {
        return List.of("Feed", "Bathe", "Take a walk", "Pamper");
    }

    @Override
    public boolean wantsToPlay() {
        return true;
    }

    @Override
    public void onTemperatureChange(Pet pet) {

    }
}
