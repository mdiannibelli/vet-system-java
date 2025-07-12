package domain.factories;

import domain.entities.healthstates.*;
import domain.interfaces.HealthState;
import enums.Species;

/**
 * Factory para crear estados de salud específicos según la especie
 * Permite obtener recomendaciones personalizadas para cada tipo de mascota
 */
public class HealthStateFactory {

    /**
     * Crea un estado de salud saludable específico para la especie
     * 
     * @param species La especie de la mascota
     * @return El estado de salud apropiado
     */
    public static HealthState createHealthyState(Species species) {
        return switch (species) {
            case DOG -> new DogHealthy();
            case CAT -> new CatHealthy();
            case RABBIT -> new RabbitHealthy();
            default -> new Healthy(); // Estado genérico para especies no específicas
        };
    }

    /**
     * Crea un estado de salud en observación específico para la especie
     * 
     * @param species La especie de la mascota
     * @return El estado de salud en observación apropiado
     */
    public static HealthState createInObservationState(Species species) {
        // Por ahora usamos el estado genérico, pero se puede extender
        return new InObservation();
    }

    /**
     * Crea un estado de salud de cuidados especiales específico para la especie
     * 
     * @param species La especie de la mascota
     * @return El estado de salud de cuidados especiales apropiado
     */
    public static HealthState createSpecialCareState(Species species) {
        // Por ahora usamos el estado genérico, pero se puede extender
        return new SpecialCare();
    }
}