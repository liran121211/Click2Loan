package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ModifyAccountController {

    @FXML
    private Button updateButton, cancelButton, deleteAccountButton;
    @FXML
    private TextField username, email, password;

    public void initManager(ModifyAccountManager modifyAccountManager) {
        updateButton.setOnAction(event -> modifyAccountManager.updateFormData(this));
        cancelButton.setOnAction(event -> {
            try {
                modifyAccountManager.goBack();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // show delete button only for clients
        deleteAccountButton.setVisible(LoginManager.logged_in_user.getInt("role", -1) == 0);
        deleteAccountButton.setOnAction(event -> modifyAccountManager.deleteUserAccount());

    }

    public TextField getUsername() {
        return username;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getPassword() {
        return password;
    }
}
