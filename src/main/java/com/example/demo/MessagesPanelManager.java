package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagesPanelManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;

    public MessagesPanelManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("messagesPanel.fxml")
            );
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            MessagesPanelController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewMessage(MessageModel selectedMessage) {
        if (selectedMessage != null) {
            ViewMessageManager viewMessageManager = new ViewMessageManager(scene);
            viewMessageManager.initializeScreen(selectedMessage);
        }
    }

    public void replyMessage(MessageModel selectedMessage) {
        if (selectedMessage != null) {
            ReplyMessageManager replyMessageManager = new ReplyMessageManager(scene);
            replyMessageManager.initializeScreen(selectedMessage);
        }
    }

    public void deleteMessage(TableView<MessageModel> messages_list) {
        if (messages_list.getSelectionModel().getSelectedItem() != null) {
            LoanApp.sql.delete("mailbox", String.format("id=%s", messages_list.getSelectionModel().getSelectedItem().getID()));
            messages_list.getItems().remove(messages_list.getSelectionModel().getSelectedItem());
        }
    }


    protected void goBack() {
        int role = LoginManager.logged_in_user.getInt("role", -1);
        if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 0) {
            UserPanelManager userPanelManager = new UserPanelManager(scene);
            userPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }
    }

    public void manage() {
        ManageUsersManager modifyUserManager = new ManageUsersManager(scene);
        modifyUserManager.initializeScreen();
    }

    public void edit() {
        EditProfileManager editProfileManager = new EditProfileManager(scene);
        editProfileManager.initializeScreen();
    }

    public void logOut() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}
