package domain.entities.healthstates;

import domain.entities.Pet;
import domain.interfaces.HealthState;

import java.util.List;

public class RabbitHealthy implements HealthState {

    @Override
    public List<String> getCareInstructions() {
        return List.of(
                "Jaula espaciosa con área de ejercicio",
                "Alimentación rica en heno (70% de la dieta)",
                "Verduras frescas diarias",
                "Agua limpia siempre disponible",
                "Limpieza de jaula cada 2-3 días",
                "Ejercicio diario fuera de la jaula",
                "Revisión dental anual",
                "Ambiente fresco y bien ventilado",
                "Socialización suave y respetuosa");
    }

    @Override
    public boolean wantsToPlay() {
        return true;
    }

    @Override
    public void onTemperatureChange(Pet pet) {
        // Los conejos saludables no requieren acción especial en cambios de temperatura
        // normales
    }
}