package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMessageManager {
    private final Scene scene;
    private final PropertyChangeSupport notifier;

    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public SendMessageManager(Scene scene) {
        this.scene = scene;
        this.notifier = new PropertyChangeSupport(this);
    }


    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("sendMessage.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            SendMessageController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void returnToMessages() {
        MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
        messagesPanelManager.initializeScreen();
    }


    public void sendMessage(String subject, String body, String banker_email) {
        LocalDate current_time = LocalDate.now();
        try {
            int fetch_sender_id = LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST);
            String fetch_banker_id = LoanApp.sql.select("users", "id", String.format("email='%s'", banker_email))[0][0];

            String columns = "sender, receiver, subject, body, date_sent, read";
            LoanApp.sql.insert("mailbox", String.format("%s", columns), String.format("%s,%s,'%s','%s',TO_DATE('%s', 'YYYY-MM-DD'),CAST(0 AS BIT)", fetch_sender_id, fetch_banker_id, subject, body, current_time));
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
