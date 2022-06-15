package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoansGraphManager {
    private final Scene scene;

    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public LoansGraphManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("loansGraphs.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            LoansGraphController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void goBack(){
        int role = LoginManager.logged_in_user.getInt("role", -1);
        if(role == 1){
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        }
        else if(role == 2){
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }
    }

    public Scene getScene() {
        return scene;
    }


}
