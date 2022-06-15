package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMessageManager {
    private final Scene scene;
    private MessageModel messageModel;

    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public ViewMessageManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen(MessageModel selectedMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("viewMessage.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            this.messageModel = selectedMessage;
            ViewMessageController controller = loader.getController();
            loadMessageData(controller);
            controller.initManager(this);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMessageData(ViewMessageController controller) {
        LoanApp.sql.update("mailbox", "read", "1", String.format("id=%s", messageModel.getID())); // set message as read.
        controller.message_subject.setEditable(false);
        controller.message_body.setEditable(false);
        controller.message_subject.setText(messageModel.getSubject());
        controller.message_body.setText(messageModel.getMessage());
        controller.sender_username.setText(String.format("From: #%s", messageModel.getSender()));
    }

    protected void goBack() {
        int role = LoginManager.logged_in_user.getInt("role", -1);
        if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }
    }

    public void returnToMessages() {
        MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
        messagesPanelManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}
