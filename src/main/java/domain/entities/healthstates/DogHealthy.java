package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;

import java.util.List;

public class DogHealthy implements HealthState {

    @Override
    public List<String> getCareInstructions() {
        return List.of(
                "Paseo diario de 30-60 minutos",
                "Vacunas al día (anuales)",
                "Alimentación balanceada 2-3 veces al día",
                "Cepillado semanal del pelaje",
                "Ejercicio físico regular",
                "Socialización con otros perros",
                "Entrenamiento básico de obediencia");
    }

    @Override
    public boolean wantsToPlay() {
        return true;
    }

    @Override
    public void onTemperatureChange(Pet pet) {
        // Los perros saludables no requieren acción especial en cambios de temperatura
        // normales
    }
}