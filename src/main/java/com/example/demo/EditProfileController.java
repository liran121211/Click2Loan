package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditProfileController {

    @FXML
    private Button updateButton, cancelButton;
    @FXML
    private TextField firstname, lastname, address, city, zipcode, country, phone, bank_number;
    @FXML
    private Label bank_account_label;

    public void initManager(EditProfileManager editProfileManager) {
        updateButton.setOnAction(event -> editProfileManager.updateFormData(this));
        cancelButton.setOnAction(event -> {
            try {
                editProfileManager.goBack();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 1) {
            bank_number.setVisible(false);
            bank_account_label.setVisible(false);
        }

    }
    public TextField getFirstname() {
        return firstname;
    }

    public TextField getLastname() {
        return lastname;
    }

    public TextField getAddress() {
        return address;
    }

    public TextField getCity() {
        return city;
    }

    public TextField getZipcode() {
        return zipcode;
    }

    public TextField getCountry() {
        return country;
    }

    public TextField getPhone() {
        return phone;
    }

    public TextField getBank_number() {
        return bank_number;
    }
}
