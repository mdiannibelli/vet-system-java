package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;

import java.util.List;

public class SpecialCare implements HealthState {
    @Override
    public List<String> getCareInstructions() {
        return List.of("Better nutrition", "Control hydration", "More physical activities");
    }

    @Override
    public boolean wantsToPlay() {
        int randomNumber = (int) (Math.random() * 2) + 1;
        return randomNumber == 1;
    }

    @Override
    public void onTemperatureChange(Pet pet) {

    }
}
