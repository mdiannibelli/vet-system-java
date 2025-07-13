package controller;

import javax.swing.JOptionPane;

import dao.AdoptionDAO;
import domain.entities.Adoption;
import exceptions.DatabaseException;
import infraestructure.daos.AdoptionDAOImpl;
import views.AdoptionsView;
import views.MainMenuView;

import java.util.List;

public class AdoptionsControllers {
    private final AdoptionsView view;
    private final AdoptionDAO adoptionDAO;

    public AdoptionsControllers(AdoptionsView adoptionsView) {
        this.view = adoptionsView;
        try {
            this.adoptionDAO = new AdoptionDAOImpl();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
            throw new DatabaseException("No se pudo inicializar el DAO de adopciones", e);
        }
        loadAdoptions();
        view.setDeleteListener(id -> eliminarAdopcion(id));
        view.setEditHealthListener(adoptionId -> editHealthState(adoptionId));
        view.setBackListener(() -> backToMainMenu());
    }

    private void eliminarAdopcion(int id) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar la adopción?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            adoptionDAO.deleteAdoption(id);
            loadAdoptions();
        }
    }

    private void loadAdoptions() {
        List<Adoption> adopciones = adoptionDAO.findAll();
        view.loadAdoptions(adopciones);
    }

    public void deleteAdoption(int id) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar la adopción?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            adoptionDAO.deleteAdoption(id);
            loadAdoptions();
        }
    }

    private void editHealthState(int adoptionId) {
        Adoption adoption = adoptionDAO.findById(adoptionId);
        if (adoption != null && adoption.getPet() != null) {
            int petId = adoption.getPet().getId();
            System.out.println("pet id" + petId);

            String[] options = { "Healthy", "InObservation", "SpecialCare" };
            String currentState = adoption.getPet().getState().getClass().getSimpleName();

            String newState = (String) JOptionPane.showInputDialog(
                    view,
                    "Selecciona el nuevo estado de salud para " + adoption.getPet().getName() + ":\nEstado actual: "
                            + currentState,
                    "Editar Estado de Salud",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    currentState);

            if (newState != null && !newState.equals(currentState)) {
                // Actualizar en la base de datos
                try {
                    dao.PetDAO petDAO = new infraestructure.daos.PetDAOImpl();
                    petDAO.updateHealthState(petId, newState);
                    JOptionPane.showMessageDialog(view, "Estado de salud actualizado correctamente.");
                    loadAdoptions(); // Recargar la lista
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Error al actualizar el estado de salud: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo encontrar la mascota de esta adopción.");
        }
    }

    private void backToMainMenu() {
        view.dispose();
        new MainMenuController(new MainMenuView());
    }
}
