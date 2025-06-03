package org.app;

import domain.entities.Adopter;
import domain.entities.Dog;
import domain.entities.Employee;
import domain.entities.healthstates.Healthy;
import domain.entities.healthstates.InObservation;
import domain.repositories.AdoptionRepository;
import enums.Species;
import infraestructure.repositories.AdoptionRepositoryImpl;
import presentation.Vet;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee employee = Employee.create(
                "Juan Pérez",
                35,
                "Av Nazca 2123",
                new Date(),
                "Encargado de Adopciones"
        );
        Vet vet = new Vet(employee, new AdoptionRepositoryImpl());

        Adopter adopter = new Adopter(
                "Pepito Suarez",
                24,
                "Av Rivadavia 5204",
                new Date()
        );

        Dog dog = new Dog(
                "Firulais",
                new Date(),
                49.2,
                37.5,
                Species.DOG,
                new InObservation()
        );

        dog.setTemperature(32.4);

        vet.registerAdoption(adopter, dog);
    }
}
