package controller;

import domain.entities.*;
import domain.entities.healthstates.Healthy;
import domain.factories.AdoptionFactory;
import domain.factories.HealthStateFactory;
import domain.interfaces.HealthState;
import enums.Species;
import dao.AdopterDAO;
import dao.PetDAO;
import dao.AdoptionDAO;
import infraestructure.daos.AdopterDAOImpl;
import infraestructure.daos.PetDAOImpl;
import infraestructure.daos.AdoptionDAOImpl;
import views.AdoptionRegisterView;
import views.TicketAdoptionView;
import exceptions.*;

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
            JOptionPane.showMessageDialog(null, "Error al inicializar DAOs: " + e.getMessage());
            throw new DatabaseException("No se pudieron inicializar los DAOs", e);
        }

    }

    public void showRecommendations() {
        try {
            String selectedSpecie = (String) view.cmbSpecie.getSelectedItem();
            if (selectedSpecie == null || selectedSpecie.isEmpty()) {
                view.txtRecommendations
                        .setText("Seleccione una especie para ver las recomendaciones de cuidado específicas.");
                return;
            }

            Species species = Species.valueOf(selectedSpecie);
            HealthState healthState = HealthStateFactory.createHealthyState(species);
            Pet tempPet = new AdoptablePet("Temp", new Date(), 1.0, 37.0, species, healthState);

            var careInstructions = tempPet.getState().getCareInstructions();
            String recommendations = String.join("\n• ", careInstructions);

            if (!recommendations.isEmpty()) {
                recommendations = "• " + recommendations;
            }

            String specieDisplayName = selectedSpecie.equals("DOG") ? "perro"
                    : selectedSpecie.equals("CAT") ? "gato"
                            : selectedSpecie.equals("RABBIT") ? "conejo" : selectedSpecie.toLowerCase();

            view.txtRecommendations.setText("Recomendaciones para " + specieDisplayName + ":\n\n" + recommendations);

        } catch (IllegalArgumentException e) {
            throw new SpeciesException(
                    "Especie no válida: " + view.cmbSpecie.getSelectedItem() + ". Use: DOG, CAT, RABBIT", e);
        } catch (Exception e) {
            throw new AdoptionException("Error al obtener recomendaciones", e);
        }
    }

    public void registerAdoption() {
        try {
            validateRequiredFields();

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

            String selectedSpecie = (String) view.cmbSpecie.getSelectedItem();
            Species petSpecies = Species.valueOf(selectedSpecie);
            HealthState healthState = HealthStateFactory.createHealthyState(petSpecies);

            Pet pet = new AdoptablePet(petName, petBirthdate, petWeight, 37.0, petSpecies, healthState);
            petDAO.save(pet);

            // Adoption
            Adoption adoption = AdoptionFactory.create(adopter, Employee.getInstance(), pet);
            adoptionDAO.save(adoption);
            Ticket ticket = adoption.generateTicket();
            new TicketAdoptionView(ticket.getTicketText());
            view.dispose();

        } catch (NumberFormatException e) {
            throw new ValidationException("La edad y el peso deben ser números válidos", e);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Formato de fecha inválido. Use YYYY-MM-DD", e);
        } catch (SpeciesException | ValidationException | DatabaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            throw new AdoptionException("Error inesperado al registrar adopción", e);
        }
    }

    private void validateRequiredFields() {
        if (view.txtAdopterName.getText().isEmpty() ||
                view.txtAdopterAge.getText().isEmpty() ||
                view.txtAdopterAddress.getText().isEmpty() ||
                view.txtPetName.getText().isEmpty() ||
                view.cmbSpecie.getSelectedItem() == null ||
                view.txtBirthdate.getText().isEmpty() ||
                view.txtWeight.getText().isEmpty()) {
            throw new ValidationException("Todos los campos son obligatorios");
        }
    }
}
