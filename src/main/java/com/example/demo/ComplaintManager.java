package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComplaintManager {

    private final Scene scene;
    private final PropertyChangeSupport notifier;
    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public ComplaintManager(Scene scene) {
        this.scene = scene;
        this.notifier = new PropertyChangeSupport(this);
    }


    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("complaintForm.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            ComplaintController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void returnToMessages() {
        MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
        messagesPanelManager.initializeScreen();
    }


    public void sendMessage(String subject, String body, String complaint) {
        LocalDate current_time = LocalDate.now();
        try {
            int fetch_sender_id = LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST);

            String fetch_admin = LoanApp.sql.select("users", "id", "role=2")[0][0];
            //System.out.println(fetch_admin);
            String columns = "sender, receiver, subject, body, date_sent, read";
            LoanApp.sql.insert("mailbox", String.format("%s", columns), String.format("%s,%s,'%s :: %s','%s',TO_DATE('%s', 'YYYY-MM-DD'),CAST(0 AS BIT)", fetch_sender_id, fetch_admin, complaint, subject, body, current_time));
            notifier.firePropertyChange("MESSAGE", null, this.scene);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PropertyChangeSupport getNotifier() {
        return notifier;
    }

    public Scene getScene() {
        return scene;
    }
}
