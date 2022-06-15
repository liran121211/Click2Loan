package com.example.demo;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.LoanApp.sql;

public class ModifyAccountManager {
    private final Scene scene;

    public ModifyAccountManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("modifyAccount.fxml")
            );
            scene.setRoot(loader.load());
            ModifyAccountController controller = loader.getController();
            controller.initManager(this);
            scene.setUserData(loader);
            fetchFormData(controller);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * go back function from edit profile panel to ither user panel or manager panel
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

    private void fetchFormData(ModifyAccountController c) {
        try {
            String[] user_data = LoanApp.sql.select("users", "*", String.format("id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)))[0];
            c.getUsername().setText(user_data[1]);
            c.getEmail().setText(user_data[3]);
            c.getPassword().setText(user_data[2]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void updateFormData(ModifyAccountController c) {
        String columns = "username, email, password";
        String data = String.format("%s,%s,%s", c.getUsername().getText(), c.getEmail().getText(), c.getPassword().getText());
        LoanApp.sql.update("users", columns, data, String.format("id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
        try {
            goBack();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteUserAccount() {
        double total = 0.0;
        try {
            for (String[] value : sql.select("loans", "loan_amount", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST))))
                total += Double.parseDouble(value[0]);

            Alert alert = new Alert(Alert.AlertType.WARNING, "Your loans, account, messages will be permanently deleted and will not be recoverable. Are you sure you want to continue?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES)
                if (total > 0.0) {
                    alert = new Alert(Alert.AlertType.INFORMATION, "There is still remaining balance on one or more of your loans, therefore it is not possible to delete your account.", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    LoanApp.sql.delete("users", String.format("id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Your account has been successfully deleted, you are now redirected to the login page.", ButtonType.OK);
                    alert.showAndWait();
                    LoginManager loginManager = new LoginManager(scene);
                    loginManager.initializeScreen();
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
