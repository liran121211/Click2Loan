package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WelcomeManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 477;
    private final static int WINDOW_HEIGHT = 407;

    public WelcomeManager(Scene scene) {
        this.scene = scene;
    }

    /**
     * go from welcome page to login page
     */
    public void login() {
        LoginManager log = new LoginManager(scene);
        log.initializeScreen();
    }

    /**
     * go from welcome page to loan page
     */
    public void loan() {
        LoanManager loanManager = new LoanManager(scene);
        loanManager.initializeScreen();
    }

    /**
     * show welcome screen
     */
    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
            scene.setRoot(loader.load());
            scene.setUserData(loader);

            if (scene.getWindow() != null) {
                this.scene.getWindow().setWidth(WINDOW_WIDTH);
                this.scene.getWindow().setHeight(WINDOW_HEIGHT);
                this.scene.setUserData(loader);
            }

            WelcomeController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException e) {
            Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
