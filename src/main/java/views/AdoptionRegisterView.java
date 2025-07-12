package views;

import javax.swing.*;

import controller.RegisterAdptionController;

import java.awt.*;

public class AdoptionRegisterView extends JFrame {
    public JTextField txtAdopterName, txtAdopterAge, txtAdopterAddress;
    public JTextField txtPetName, txtSpecie, txtBirthdate, txtWeight;
    public JButton registerBtn;
    public JTextArea txtRecommendations;

    public AdoptionRegisterView() {
        this.createView();

        RegisterAdptionController registerController = new RegisterAdptionController(this);

        this.registerBtn.addActionListener(e -> registerController.registerAdoption());
        this.txtSpecie.addActionListener(e -> registerController.showRecommendations());
    }

    private void createView() {
        setTitle("Adoption Register");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(13, 1));

        txtAdopterName = new JTextField();
        txtAdopterAge = new JTextField();
        txtAdopterAddress = new JTextField();

        txtPetName = new JTextField();
        txtSpecie = new JTextField();
        txtBirthdate = new JTextField();
        txtWeight = new JTextField();

        txtRecommendations = new JTextArea(3, 20);
        txtRecommendations.setEditable(false);

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
        add(txtSpecie);
        add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        add(txtBirthdate);
        add(new JLabel("Peso (kg):"));
        add(txtWeight);

        add(new JLabel("Recomendaciones:"));
        add(txtRecommendations);
        add(registerBtn);

        setVisible(true);
    }
}
