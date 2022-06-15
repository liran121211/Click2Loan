package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InspectLoanManager {
    private final Scene scene;

    private final static int WINDOW_WIDTH = 495;
    private final static int WINDOW_HEIGHT = 675;

    public InspectLoanManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen(int loan_id) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("inspectLoan.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            InspectLoanController controller = loader.getController();
            controller.initManager(this, loan_id);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void goBack() {
        int role = LoginManager.logged_in_user.getInt("role", -1);
        if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 0) {
            UserPanelManager userPanelManager = new UserPanelManager(scene);
            userPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }
    }

    public Scene getScene() {
        return scene;
    }


}
