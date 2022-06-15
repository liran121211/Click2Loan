package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.demo.LoanApp.sql;


public class UserPanelManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 650;
    private final static int WINDOW_HEIGHT = 650;

    public UserPanelManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("userPanel.fxml")
            );
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);
            UserPanelController controller = loader.getController();

            controller.getAccountLabel().setText(AccountFullName());
            controller.getBalanceLabel().setText("$" + totalLoanRemaining());

            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Double totalLoanRemaining() throws SQLException {
        double total = 0.0;
        for (String[] value : sql.select("clients", "credits", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST))))
            total += Double.parseDouble(value[0]);
        return total;
    }

    private String AccountFullName() throws SQLException {
        String[][] full_name = sql.select("clients", "first_name, last_name", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
        if (full_name.length != 0)
            return full_name[0][0] + " " + full_name[0][1];
        else
            return "NOT AVAILABLE";
    }


    public void goLogin() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
    }

    public void edit() {
        EditProfileManager editProfileManager = new EditProfileManager(scene);
        editProfileManager.initializeScreen();
    }

    protected void sendMessageBanker() {
        SendMessageManager sendMessageManager = new SendMessageManager(scene);
        sendMessageManager.initializeScreen();
    }

    public void goComplaint() {
        ComplaintManager complaintManager = new ComplaintManager(scene);
        complaintManager.initializeScreen();
    }

    public void Withdraw() {
        WithdrawManager withdrawManager = new WithdrawManager(scene);
        withdrawManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}
