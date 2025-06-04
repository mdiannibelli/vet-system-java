package org.app;

import domain.entities.*;
import domain.entities.healthstates.Healthy;
import domain.entities.healthstates.InObservation;
import domain.entities.healthstates.SpecialCare;
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

        Adopter adopter2 = new Adopter(
                "Maria Lopez",
                65,
                new Date(),
                "Av Cabildo 592"
        );

        Dog dog = new Dog(
                "Firulais",
                new Date(),
                49.2,
                37.5,
                Species.DOG,
                new InObservation()
        );

        Cat cat = new Cat(
                "Botas",
                new Date(),
                49.2,
                37.5,
                Species.CAT,
                new Healthy()
        );

        Rabbit rabbit = new Rabbit(
                "Pituso",
                new Date(),
                22.2,
                25.5,
                Species.RABBIT,
                new SpecialCare()
        );

        vet.registerPet(dog); // if we don't register the new pet, it will throw an AdoptionException error
        vet.registerPet(cat);
        vet.registerPet(rabbit);


        dog.setTemperature(32.4);

        vet.registerAdoption(adopter, dog);
        vet.registerAdoption(adopter2, rabbit);

        ContainerObj<ContainerActions> container = new ContainerObj<>(new Bed());
        System.out.println("¿It is empty? " + container.isEmpty());
        System.out.println("¿It is a pet? " + container.getItem().isPet());

        vet.listPetsWithSpecialCare();
        vet.listAdopterNames();
        vet.countPetsPerSpecie();
        vet.findPetsWithMinimumWeight(30);
        vet.showPetsOrderByName();
        vet.averageAdopterAges();
        vet.filterRecentAdoptions();
        vet.transformPet();
    }
}
