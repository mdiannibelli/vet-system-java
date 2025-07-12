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
            view.txtRecommendations.setText("Especie no válida. Use: DOG, CAT, RABBIT");
        } catch (Exception e) {
            view.txtRecommendations.setText("Error al obtener recomendaciones: " + e.getMessage());
        }
    }

    public void registerAdoption() {
        try {
            if (view.txtAdopterName.getText().isEmpty() ||
                    view.txtAdopterAge.getText().isEmpty() ||
                    view.txtAdopterAddress.getText().isEmpty() ||
                    view.txtPetName.getText().isEmpty() ||
                    view.cmbSpecie.getSelectedItem() == null ||
                    view.txtBirthdate.getText().isEmpty() ||
                    view.txtWeight.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
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
            JOptionPane.showMessageDialog(null, "Error: Asegúrese de que la edad y el peso sean números válidos.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: Formato de fecha inválido. Use YYYY-MM-DD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar adopción: " + e.getMessage());
        }
    }
}
