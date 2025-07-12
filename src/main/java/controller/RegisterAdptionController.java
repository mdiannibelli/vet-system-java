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

    }

    public void showRecommendations() {
        String specie = view.txtSpecie.getText().toLowerCase();
        String recommendations = switch (specie) {
            case "dog" -> "Paseo diario, vacunas al día, buena alimentación.";
            case "cat" -> "Espacios tranquilos, caja de arena limpia, juego diario.";
            default -> "Consulta con el veterinario según la especie.";
        };
        view.txtRecommendations.setText(recommendations);
    }

    public void registerAdoption() {
        try {
            if (view.txtAdopterName.getText().isEmpty() ||
                    view.txtAdopterAge.getText().isEmpty() ||
                    view.txtAdopterAddress.getText().isEmpty() ||
                    view.txtPetName.getText().isEmpty() ||
                    view.txtSpecie.getText().isEmpty() ||
                    view.txtBirthdate.getText().isEmpty() ||
                    view.txtWeight.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            // Adopter
            String name = view.txtAdopterName.getText();
            int age = Integer.parseInt(view.txtAdopterAge.getText());
            String address = view.txtAdopterAddress.getText();
            Date birthdate = java.sql.Date.valueOf(view.txtBirthdate.getText()); // formato: "yyyy-MM-dd"

            Adopter adopter = new Adopter(name, age, birthdate, address);
            adopterDAO.save(adopter);

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

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Please make sure the age and weight are valid numbers.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid date format. Use YYYY-MM-DD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error registering adoption: " + e.getMessage());
        }
    }
}
