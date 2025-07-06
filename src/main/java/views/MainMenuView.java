package views;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
    public JButton btnRegisterAdoption, btnLogOut;

    public MainMenuView() {
        setTitle("Main menu");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnRegisterAdoption = new JButton("New adoption");
        btnLogOut = new JButton("Log out");

        add(btnRegisterAdoption);
        add(btnLogOut);

        setVisible(true);
    }
}
