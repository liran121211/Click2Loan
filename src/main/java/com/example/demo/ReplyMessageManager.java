package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReplyMessageManager {
    private final Scene scene;
    private MessageModel messageModel;

    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public ReplyMessageManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen(MessageModel selectedMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("replyMessage.fxml")
            );
            this.scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);

            this.messageModel = selectedMessage;
            ReplyMessageController controller = loader.getController();
            loadMessageData(controller);
            controller.initManager(this);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadMessageData(ReplyMessageController controller) {
        LoanApp.sql.update("mailbox", "read", "1", String.format("id=%s", messageModel.getID())); // set message as read.
        controller.message_subject.setText("Reply: " + messageModel.getSubject());
        controller.message_body.setText(" \n" + "--------------------------------------------------------------------------------------------\n" + messageModel.getMessage());
        controller.replying_to.setText(String.format("Reply #%s", messageModel.getSender()));
    }

    public void returnToMessages() {
        MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
        messagesPanelManager.initializeScreen();
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

    public void sendReplyMessage(String subject, String body, ReplyMessageController c) {
        try {
            LocalDate current_time = java.time.LocalDate.now();
            //System.out.println(current_time);
            String columns = "sender, receiver, subject, body, date_sent, read";
            String[][] fetch_message = LoanApp.sql.select("mailbox", "*", String.format("id=%s", messageModel.getID()));
            LoanApp.sql.insert("mailbox", String.format("%s", columns), String.format("%s,%s,'%s','%s',TO_DATE('%s', 'YYYY-MM-DD'),CAST(0 AS BIT)", fetch_message[0][2], fetch_message[0][1], subject.replace("'", ""), body.replace("'", ""), current_time));

            c.exit_message.setDisable(true);
            c.send_message.setDisable(true);
            this.scene.getRoot().setEffect(new BoxBlur(5.0, 5.0, 1));
            c.message_sent_popup.setVisible(true);
            new Timeline(new KeyFrame(Duration.seconds(3), event -> returnToMessages())).play();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
