package views;

import controller.MainMenuController;
import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    public JButton btnRegisterAdoption, btnLogOut, btnShowAdoptions;

    public MainMenuView() {
        this.createView();

        MainMenuController mainMenuController = new MainMenuController(this);

        this.btnRegisterAdoption.addActionListener(e -> mainMenuController.openRegisterAdoption());
        this.btnShowAdoptions.addActionListener(e -> mainMenuController.showAdoptions());
        this.btnLogOut.addActionListener(e -> mainMenuController.logOut());
    }

    private void createView() {
        setTitle("Main menu");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnRegisterAdoption = new JButton("New adoption");
        btnShowAdoptions = new JButton("Show Adoptions");
        btnLogOut = new JButton("Log out");

        add(btnRegisterAdoption);
        add(btnShowAdoptions);
        add(btnLogOut);

        setVisible(true);
    }
}
