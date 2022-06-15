package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.sql.SQLException;


/**
 * Controls the login screen
 */
public class LoginController {
    @FXML
    private TextField user, password;
    @FXML
    private Button loginButton, back, forgot_password;
    @FXML
    private Label login_status;

    private LoginManager loginMan;

    /**
     * init button in login scene
     */
    public void initManager(LoginManager loginManager) {
        loginMan = loginManager;
        login_status.setVisible(false);

        loginButton.setOnAction(event -> {
            try {
                if (user.getText().length() == 0 || password.getText().length() == 0)
                    incorrectLogin();
                if (loginManager.authorize(user.getText(), password.getText()) == LoanApp.USER_NOT_EXIST)
                    incorrectLogin();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> loginManager.goWelcome());

        forgot_password.setOnAction(actionEvent -> loginManager.forgotPassword());
    }

    protected void incorrectLogin() {
        Timeline msg_flasher = new Timeline(
                new KeyFrame(Duration.seconds(0.2), e -> login_status.setVisible(true)),
                new KeyFrame(Duration.seconds(3.0), e -> login_status.setVisible(false)));
        msg_flasher.setCycleCount(1);
        msg_flasher.play();
    }

    public TextField getUser() {
        return user;
    }

    public TextField getPassword() {
        return password;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getBack() {
        return back;
    }

    @FXML
    public void onEnter(ActionEvent event){
        try {
            if (user.getText().length() == 0 || password.getText().length() == 0)
                incorrectLogin();
            if (loginMan.authorize(user.getText(), password.getText()) == LoanApp.USER_NOT_EXIST)
                incorrectLogin();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

