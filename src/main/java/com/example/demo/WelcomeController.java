package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class WelcomeController {

    @FXML
    private Button login, ask_for_loan;


    public void initManager(WelcomeManager welcomeManager) {
        login.setOnAction(actionEvent -> welcomeManager.login());
        ask_for_loan.setOnAction((actionEvent -> welcomeManager.loan()));
    }


    public Button getLogin() {
        return login;
    }
}
