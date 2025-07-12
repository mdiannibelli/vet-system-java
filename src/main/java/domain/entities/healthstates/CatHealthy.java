package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;

import java.util.List;

public class CatHealthy implements HealthState {

    @Override
    public List<String> getCareInstructions() {
        return List.of(
                "Espacios tranquilos y seguros",
                "Caja de arena limpia (limpiar diariamente)",
                "Juego diario con juguetes interactivos",
                "Alimentación controlada 2-3 veces al día",
                "Agua fresca siempre disponible",
                "Cepillado regular del pelaje",
                "Rascador para mantener uñas sanas",
                "Vacunas anuales obligatorias");
    }

    @Override
    public boolean wantsToPlay() {
        return true;
    }

    @Override
    public void onTemperatureChange(Pet pet) {
        // Los gatos saludables no requieren acción especial en cambios de temperatura
        // normales
    }
}