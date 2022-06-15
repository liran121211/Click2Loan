package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerPanelManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 650;
    private final static int WINDOW_HEIGHT = 650;
    private final PropertyChangeSupport notifier;


    public ManagerPanelManager(Scene scene) {
        this.scene = scene;
        this.notifier = new PropertyChangeSupport(this);
    }


    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("managerPanel.fxml"));
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            ManagerPanelController controller = loader.getController();
            notifier.addPropertyChangeListener(controller); // to notify controller
            controller.initManager(this);

            // Infinity Thread worker to check for new messages
            //--------------------------------
            fetchMessages();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                try {
                    fetchMessages();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            //--------------------------------

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

    public void modifyAccount() {
        ModifyAccountManager modifyAccountManager = new ModifyAccountManager(scene);
        modifyAccountManager.initializeScreen();
    }

    public void manageClients() {
        ManageUsersManager manageUserManager = new ManageUsersManager(scene);
        manageUserManager.initializeScreen();
    }

    public void manageMessages() {
        MessagesPanelManager manageMessagesManager = new MessagesPanelManager(scene);
        manageMessagesManager.initializeScreen();
    }

    /**
     * Retrieve all the messages belong to the specific user.
     */
    private void fetchMessages() throws SQLException {
        String[][] unread_messages = LoanApp.sql.select("mailbox", "read", String.format("read=CAST(0 AS BIT),receiver=%s", LoginManager.logged_in_user.getInt("userid", -1)));
        if (unread_messages.length > 0)
            notifier.firePropertyChange("NEW_MESSAGE", 0, unread_messages.length);
        if (unread_messages.length == 0)
            notifier.firePropertyChange("NO_NEW_MESSAGES", -1, unread_messages.length);
    }

    public Scene getScene() {
        return scene;
    }
}
