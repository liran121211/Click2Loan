package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewLoansManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 650;
    private final static int WINDOW_HEIGHT = 650;


    public ViewLoansManager(Scene scene) {
        this.scene = scene;
    }


    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewLoansByManager.fxml"));
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            ViewLoansController controller = loader.getController();
            controller.initManager(this);
            countStatus(controller);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goLogin() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
    }

    public void edit() {
        EditProfileManager editProfileManager = new EditProfileManager(scene);
        editProfileManager.initializeScreen();
    }

    private void countStatus(ViewLoansController c) {
        try {
            String[][] status_counter = LoanApp.sql.select("loans", "status");
            int approved = 0, pending = 0, rejected = 0;
            for (String[] status : status_counter) {
                if (status[0].compareTo("APPROVED") == 0)
                    approved++;
                if (status[0].compareTo("PENDING") == 0)
                    pending++;
                if (status[0].compareTo("REJECTED") == 0)
                    rejected++;
            }

            c.getRejected_count().setText("(" + rejected + " Rejected)");
            c.getPending_count().setText("(" + pending + " Pending)");
            c.getApproved_count().setText("(" + approved + " Approved)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void inspectLoan() {
        InspectLoanManager inspectLoanManager = new InspectLoanManager(scene);
        inspectLoanManager.initializeScreen(((BankerPanelController) ((FXMLLoader) scene.getUserData()).getController()).getLoans_list().getSelectionModel().getSelectedItem().getId());
    }

    public Scene getScene() {
        return scene;
    }
}
