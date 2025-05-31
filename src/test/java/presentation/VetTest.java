package presentation;

import domain.entities.*;
import domain.entities.healthstates.Healthy;
import domain.repositories.AdoptionRepository;
import enums.Species;
import infraestructure.repositories.AdoptionRepositoryImpl;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

public class VetTest {
    private Vet vet;
    private Dog dog;
    private Adopter adopter;

    @BeforeEach
    void setUp() {
        Employee mockEmployee = Employee.create("Juan Pérez",
                35,
                "Av Nazca 2123",
                new Date(),
                "Encargado de Adopciones");
        this.vet = new Vet(mockEmployee, new AdoptionRepositoryImpl());
        this.dog = new Dog("Pepito", new Date(), 9.99,37.5, Species.INVERTEBRATE, new Healthy());
        this.adopter = new Adopter("Marcos", 21, "Av Juan.B justo 1999", new Date());
    }

    @Test
    @DisplayName("It should not have any pets at Vet initialization")
    public void testInitialAdoptions(){
        Assertions.assertEquals(0, vet.getPets().size());
    }

    @Test
    @DisplayName("It should add a new pet")
    public void testRegisterPetAddsToList() {
        vet.registerPet(this.dog);
        Assertions.assertEquals(1, vet.getPets().size());
        Assertions.assertTrue(vet.getPets().contains(dog));
    }

    @Test
    @DisplayName("It should returns all persist adoptions")
    public void testPersistAdoptions() {
        List<Adoption> adoptions = vet.getAdoptions();
        Assertions.assertEquals(0, adoptions.size());

        this.vet.registerAdoption(this.adopter, this.dog);
        adoptions = vet.getAdoptions();
        Assertions.assertEquals(1, adoptions.size());
    }
}
