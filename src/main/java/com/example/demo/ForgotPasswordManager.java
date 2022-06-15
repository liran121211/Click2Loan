package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import static com.example.demo.LoanApp.sql;


/**
 * Manages control flow for logins
 */
public class ForgotPasswordManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 366;
    private final static int WINDOW_HEIGHT = 436;

    protected final static Preferences logged_in_user = Preferences.userRoot().node("AUTHORIZED_USER");

    protected final static int INVALID_USER_ID = -1;

    public ForgotPasswordManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("forgotPassword.fxml")
            );
            scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            ForgotPasswordController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * back button to go back to welcome screen
     */
    public void goWelcome() {
        WelcomeManager welcomeManager = new WelcomeManager(scene);
        welcomeManager.initializeScreen();
    }

    /**
     * Check authorization.
     */
    public int authorize(String username, String phone) throws SQLException {

        if (username.length() == 0 || phone.length() == 0)
            return INVALID_USER_ID;

        String[][] fetch_user = sql.select("users", "id", String.format("username='%s'", username));
        if (fetch_user.length == 0)
            return INVALID_USER_ID;

        String[][] fetch_phone = sql.select("clients", "id", String.format("phone='%s' , user_id=%s", phone, fetch_user[0][0]));
        if (fetch_phone.length != 0)
            return Integer.parseInt(fetch_user[0][0]);

        fetch_phone = sql.select("bankers", "id", String.format("phone='%s', user_id=%s", phone, fetch_user[0][0]));
        if (fetch_phone.length != 0)
            return Integer.parseInt(fetch_user[0][0]);

        return INVALID_USER_ID;
    }

    protected void updatePassword(TextField username, TextField phone, Label resetStatus, int user_id) {
        if (username.getText().compareTo(phone.getText()) == 0) {
            LoanApp.sql.update("users", "password", username.getText(), String.format("id=%s", user_id));

            LoginManager loginManager = new LoginManager(scene);
            loginManager.initializeScreen();
        } else {
            resetStatus.setStyle("-fx-background-color: RED; -fx-text-fill: WHITE;");
            resetStatus.setText("Passwords do not match!");
            Timeline msg_flasher = new Timeline(
                    new KeyFrame(Duration.seconds(0.2), e -> resetStatus.setVisible(true)),
                    new KeyFrame(Duration.seconds(3.0), e -> resetStatus.setVisible(false)));
            msg_flasher.setCycleCount(1);
            msg_flasher.play();
        }

    }

    public Scene getScene() {
        return scene;
    }
}