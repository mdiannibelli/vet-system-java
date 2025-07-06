package controller;

import domain.entities.*;
import domain.entities.healthstates.Healthy;
import domain.factories.AdoptionFactory;
import enums.Species;
import dao.AdopterDAO;
import dao.PetDAO;
import dao.AdoptionDAO;
import infraestructure.daos.AdopterDAOImpl;
import infraestructure.daos.PetDAOImpl;
import infraestructure.daos.AdoptionDAOImpl;
import views.AdoptionRegisterView;
import views.TicketAdoptionView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class RegisterAdptionController {
    private final AdoptionRegisterView view;
    private final AdopterDAO adopterDAO;
    private final PetDAO petDAO;
    private final AdoptionDAO adoptionDAO;

    public RegisterAdptionController(AdoptionRegisterView view) {
        this.view = view;

        try {
            adopterDAO = new AdopterDAOImpl();
            petDAO = new PetDAOImpl();
            adoptionDAO = new AdoptionDAOImpl();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at DAO: " + e.getMessage());
            throw new RuntimeException(e);
        }

        view.registerBtn.addActionListener(e -> registerAdoption());
        view.txtSpecie.addActionListener(e -> showRecommendations());
    }

    private void showRecommendations() {
        String specie = view.txtSpecie.getText().toLowerCase();
        String recommendations = switch (specie) {
            case "dog" -> "Paseo diario, vacunas al día, buena alimentación.";
            case "cat" -> "Espacios tranquilos, caja de arena limpia, juego diario.";
            default -> "Consulta con el veterinario según la especie.";
        };
        view.txtRecommendations.setText(recommendations);
    }

    private void registerAdoption() {
        try {
            // Adopter
            String name = view.txtAdopterName.getText();
            int age = Integer.parseInt(view.txtAdopterAge.getText());
            String address = view.txtAdopterAddress.getText();
            Date birthdate = java.sql.Date.valueOf(view.txtBirthdate.getText()); // formato: "yyyy-MM-dd"

            Adopter adopter = new Adopter(name, age, birthdate, address);

            // Pet
            String petName = view.txtPetName.getText();
            Date petBirthdate = java.sql.Date.valueOf(view.txtBirthdate.getText());
            double petWeight = Double.parseDouble(view.txtWeight.getText());

            Species petSpecies = Species.valueOf(view.txtSpecie.getText().toUpperCase());

            Pet pet = new AdoptablePet(petName, petBirthdate, petWeight, 37.0, petSpecies, new Healthy());
            petDAO.save(pet);

            // Adoption
            Adoption adoption = AdoptionFactory.create(adopter, Employee.getInstance(), pet);
            adoptionDAO.save(adoption);

            // Generate ticket
            Ticket ticket = adoption.generateTicket();

            // Mostrar ticket en la vista
            String ticketText = """
                    🐾 TICKET DE ADOPCIÓN 🐾

                    Empleado: %s
                    Adoptante: %s (%d años)
                    Dirección: %s

                    Mascota: %s (%s)
                    Nacimiento: %s | Peso: %.2f kg

                    Fecha de adopción: %s
                    """.formatted(
                    Employee.getInstance().getName(),
                    adopter.getName(),
                    adopter.getAge(),
                    adopter.getAddress(),
                    pet.getName(),
                    pet.getSpecie(),
                    pet.getBirthDate(),
                    pet.getWeight(),
                    adoption.getDateAdoption().toString());

            new TicketAdoptionView(ticketText);
            view.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar adopción: " + e.getMessage());
        }
    }
}
