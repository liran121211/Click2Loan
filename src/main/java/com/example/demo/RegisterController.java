package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button homeButton, confirmButton;
    @FXML
    private TextField username, email;
    @FXML
    private PasswordField password, repeat_password;
    @FXML
    private CheckBox agreementCheckBox;


    public void initManager(RegisterManager registerManager) {
        confirmButton.setOnAction(event -> {
            try {
                registerManager.createAccount(password, repeat_password, email, username, agreementCheckBox);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        homeButton.setOnAction(event -> registerManager.goWelcome());
    }


}
