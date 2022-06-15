package com.example.demo;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditProfileManager {
    private final Scene scene;

    public EditProfileManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("editProfile.fxml")
            );
            scene.setRoot(loader.load());
            EditProfileController controller = loader.getController();
            controller.initManager(this);
            scene.setUserData(loader);
            fetchFormData(controller);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * go back function from edit profile panel to user panel or manager panel
     */
    public void goBack() throws SQLException {
        int role = LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST);
        if (role == 0) {
            UserPanelManager userPanelManager = new UserPanelManager(scene);
            userPanelManager.initializeScreen();
        } else if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }

    }

    private void fetchFormData(EditProfileController c) {
        try {
            if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 0) {
                String[] user_data = LoanApp.sql.select("clients", "*", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)))[0];
                c.getFirstname().setText(user_data[1]);
                c.getLastname().setText(user_data[2]);
                c.getAddress().setText(user_data[3]);
                c.getCity().setText(user_data[4]);
                c.getCountry().setText(user_data[5]);
                c.getZipcode().setText(user_data[6]);
                c.getPhone().setText(user_data[7]);
                c.getBank_number().setText(user_data[9]);
            } else if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 1) {
                String[] user_data = LoanApp.sql.select("bankers", "*", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)))[0];
                c.getFirstname().setText(user_data[8]);
                c.getLastname().setText(user_data[2]);
                c.getAddress().setText(user_data[3]);
                c.getCity().setText(user_data[4]);
                c.getCountry().setText(user_data[5]);
                c.getZipcode().setText(user_data[6]);
                c.getPhone().setText(user_data[7]);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updateFormData(EditProfileController c) {
        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 0) {
            String columns = "first_name, last_name, street, city, country, zipcode, phone, bank_number";
            String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s", c.getFirstname().getText(), c.getLastname().getText(), c.getAddress().getText(), c.getCity().getText(), c.getCountry().getText(), c.getZipcode().getText(), c.getPhone().getText(), c.getBank_number().getText());
            LoanApp.sql.update("clients", columns, data, String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));

        } else if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 1) {
            String columns = "first_name, last_name, street, city, country, zipcode, phone";
            String data = String.format("%s,%s,%s,%s,%s,%s,%s", c.getFirstname().getText(), c.getLastname().getText(), c.getAddress().getText(), c.getCity().getText(), c.getCountry().getText(), c.getZipcode().getText(), c.getPhone().getText());
            LoanApp.sql.update("bankers", columns, data, String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
        }

        try {
            goBack();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
