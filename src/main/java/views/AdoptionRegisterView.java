package views;

import javax.swing.*;
import enums.Species;

import controller.RegisterAdptionController;

import java.awt.*;

public class AdoptionRegisterView extends JFrame {
    public JTextField txtAdopterName, txtAdopterAge, txtAdopterAddress;
    public JTextField txtPetName, txtBirthdate, txtWeight;
    public JComboBox<String> cmbSpecie;
    public JButton registerBtn;
    public JTextArea txtRecommendations;

    public AdoptionRegisterView() {
        this.createView();

        RegisterAdptionController registerController = new RegisterAdptionController(this);

        this.registerBtn.addActionListener(e -> registerController.registerAdoption());
        this.cmbSpecie.addActionListener(e -> registerController.showRecommendations());

        // Mostrar recomendaciones iniciales
        registerController.showRecommendations();
    }

    private void createView() {
        setTitle("Registro de Adopción");
        setSize(450, 650);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(13, 1));

        txtAdopterName = new JTextField();
        txtAdopterAge = new JTextField();
        txtAdopterAddress = new JTextField();

        txtPetName = new JTextField();
        cmbSpecie = new JComboBox<>(new String[] { "DOG", "CAT", "RABBIT" });
        txtBirthdate = new JTextField();
        txtWeight = new JTextField();

        txtRecommendations = new JTextArea(4, 20);
        txtRecommendations.setEditable(false);
        txtRecommendations.setLineWrap(true);
        txtRecommendations.setWrapStyleWord(true);

        registerBtn = new JButton("Registrar Adopción");

        add(new JLabel("Nombre del Adoptante:"));
        add(txtAdopterName);
        add(new JLabel("Edad del Adoptante:"));
        add(txtAdopterAge);
        add(new JLabel("Dirección del Adoptante:"));
        add(txtAdopterAddress);

        add(new JLabel("Nombre de la Mascota:"));
        add(txtPetName);
        add(new JLabel("Especie:"));
        add(cmbSpecie);
        add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        add(txtBirthdate);
        add(new JLabel("Peso (kg):"));
        add(txtWeight);

        add(new JLabel("Recomendaciones de Cuidado:"));
        add(new JScrollPane(txtRecommendations));
        add(registerBtn);

        setVisible(true);
    }
}
