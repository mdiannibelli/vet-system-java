package org.app;

import domain.entities.*;
import domain.entities.healthstates.Healthy;
import domain.entities.healthstates.InObservation;
import domain.interfaces.ContainerActions;
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
                new Date(),
                "Av Cabildo 592" // address required, otherwise it will throw an AdoptionException error
        );

        Dog dog = new Dog(
                "Firulais",
                new Date(),
                49.2,
                37.5,
                Species.DOG,
                new InObservation()
        );

        vet.registerPet(dog); // if we don't register the new pet, it will throw an AdoptionException error

        dog.setTemperature(32.4);

        vet.registerAdoption(adopter, dog);

        ContainerObj<ContainerActions> container = new ContainerObj<>(new Bed());
        System.out.println("¿It is empty? " + container.isEmpty());
        System.out.println("¿It is a pet? " + container.getItem().isPet());
    }
}
