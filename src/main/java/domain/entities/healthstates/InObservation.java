package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;
import infraestructure.helpers.EmailHelper;

import java.util.List;

public class InObservation implements HealthState {
    @Override
    public List<String> getCareInstructions() {
        return List.of("Better nutrition", "Control hydration", "More physical activities");
    }

    @Override
    public boolean wantsToPlay() {
        return false;
    }

    @Override
    public void onTemperatureChange(Pet pet) {
        String matter = pet.getName() + " just changed his temperature";
        String body = "Now his temperature is " + pet.getTemperature();
        EmailHelper.sendEmail("marcos.iannibelli@davinci.edu.ar", "mdiannibelli@gmail.com", matter, body);
    }
}
