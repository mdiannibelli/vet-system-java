package controller;

import views.AdoptionRegisterView;
import views.LoginView;
import views.MainMenuView;

public class MainMenuController {
    private final MainMenuView view;

    public MainMenuController(MainMenuView view) {
        this.view = view;

        view.btnRegisterAdoption.addActionListener(e -> openRegisterAdoption());
        view.btnLogOut.addActionListener(e -> logOut());
    }

    private void openRegisterAdoption() {
        view.dispose();
        new RegisterAdptionController(new AdoptionRegisterView());
    }

    private void logOut() {
        view.dispose();
        new LoginController(new LoginView());
    }
}

