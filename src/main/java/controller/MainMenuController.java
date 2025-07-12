package controller;

import views.AdoptionRegisterView;
import views.LoginView;
import views.MainMenuView;

public class MainMenuController {
    private final MainMenuView view;

    public MainMenuController(MainMenuView view) {
        this.view = view;
    }

    public void openRegisterAdoption() {
        view.dispose();
        new RegisterAdptionController(new AdoptionRegisterView());
    }

    public void logOut() {
        view.dispose();
        new LoginController(new LoginView());
    }
}
