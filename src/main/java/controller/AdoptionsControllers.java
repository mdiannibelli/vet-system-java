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

    private void backToMainMenu() {
        view.dispose();
        new MainMenuController(new MainMenuView());
    }
}
