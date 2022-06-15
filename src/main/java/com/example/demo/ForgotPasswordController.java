package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.sql.SQLException;


/**
 * Controls the login screen
 */
public class ForgotPasswordController {
    @FXML
    private TextField username, phone;
    @FXML
    private Button validateButton, homeButton;
    @FXML
    private Label resetStatus, usernameLabel, phoneLabel;

    /**
     * init button in login scene
     */
    public void initManager(ForgotPasswordManager forgotPasswordManager) {
        resetStatus.setVisible(false);

        validateButton.setOnAction(event -> {
            try {
                if (username.getText().length() == 0 || phone.getText().length() == 0)
                    incorrectDetails();
                if (forgotPasswordManager.authorize(username.getText(), phone.getText()) == ForgotPasswordManager.INVALID_USER_ID)
                    incorrectDetails();
                else {
                    correctDetails(forgotPasswordManager, forgotPasswordManager.authorize(username.getText(), phone.getText()));
                    usernameLabel.setText("Password: ");
                    phoneLabel.setText("Repeat Password: ");
                    username.setText("");
                    phone.setText("");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        homeButton.setOnAction(actionEvent -> forgotPasswordManager.goWelcome());
    }

    protected void incorrectDetails() {
        resetStatus.setStyle("-fx-background-color: RED; -fx-text-fill: WHITE;");
        resetStatus.setText("Username or Phone is incorrect...");
        Timeline msg_flasher = new Timeline(
                new KeyFrame(Duration.seconds(0.2), e -> resetStatus.setVisible(true)),
                new KeyFrame(Duration.seconds(3.0), e -> resetStatus.setVisible(false)));
        msg_flasher.setCycleCount(1);
        msg_flasher.play();
    }

    protected void correctDetails(ForgotPasswordManager forgotPasswordManager, int user_id) {
        resetStatus.setStyle("-fx-background-color: GREEN; -fx-text-fill: WHITE;");
        resetStatus.setText("We found your account!, reset your password...");
        validateButton.setText("Reset Password");
        validateButton.setOnAction(event -> forgotPasswordManager.updatePassword(username, phone, resetStatus, user_id));

        Timeline msg_flasher = new Timeline(
                new KeyFrame(Duration.seconds(0.2), e -> resetStatus.setVisible(true)),
                new KeyFrame(Duration.seconds(3.0), e -> resetStatus.setVisible(false)));
        msg_flasher.setCycleCount(1);
        msg_flasher.play();
    }
}

