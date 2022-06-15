package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WithdrawManager {

    private final Scene scene;
    private final static int WINDOW_WIDTH = 650;
    private final static int WINDOW_HEIGHT = 650;

    public WithdrawManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("withdraw.fxml")
            );
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);
            WithdrawController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    protected void withdrawRequest(TextField acc_num, TextField branch, TextField bank_name, TextField full_name, TextField amount) {
        int user_id = LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST);
        LocalDate current_time = LocalDate.now();
        try {
            String available_amount = LoanApp.sql.select("clients", "credits", String.format("user_id = %s", user_id))[0][0];
            String columns = "amount, bank_name, branch_code, bank_number, full_name, date, user_id";
            if (Double.parseDouble(amount.getText()) > Double.parseDouble(available_amount)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your Loan amount has exceeded the allowed amount to withdraw", ButtonType.OK);
                alert.showAndWait();
            } else {
                LoanApp.sql.insert("withdraw_requests", String.format("%s", columns), String.format("%s,'%s',%s,%s,'%s',TO_DATE('%s', 'YYYY-MM-DD'), %s", amount.getText(), bank_name.getText(), branch.getText(), acc_num.getText(), full_name.getText(), current_time, user_id));
                LoanApp.sql.update("clients", "credits", String.valueOf(Double.parseDouble(available_amount) - Double.parseDouble(amount.getText())), String.format("user_id=%s", user_id));
                UserPanelManager userPanelManager = new UserPanelManager(scene);
                userPanelManager.initializeScreen();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goBack() {
        UserPanelManager userPanelManager = new UserPanelManager(scene);
        userPanelManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}
