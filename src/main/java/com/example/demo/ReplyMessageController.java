package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ReplyMessageController {
    @FXML
    public
    Button send_message;
    @FXML
    Button exit_message;
    @FXML
    public
    TextField message_subject;
    @FXML
    TextArea message_body;
    @FXML
    Label replying_to, message_sent_popup;
    @FXML
    MenuItem homeButton, manageUsersButton, editProfileButton, modifyAccountButton, logoutButton, aboutButton;

    public void initManager(ReplyMessageManager replyMessageManager) throws SQLException {
        message_sent_popup.setVisible(false);//hide success message sent
        exit_message.setOnAction(event -> replyMessageManager.returnToMessages());
        send_message.setOnAction(event -> replyMessageManager.sendReplyMessage(message_subject.getText(), message_body.getText(), this));

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(replyMessageManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        homeButton.setOnAction(event -> replyMessageManager.goBack());

        manageUsersButton.setOnAction(event -> {
            ManageUsersManager manageUsersManager = new ManageUsersManager(replyMessageManager.getScene());
            manageUsersManager.initializeScreen();
        });

        editProfileButton.setOnAction(event -> {
            EditProfileManager editProfileManager = new EditProfileManager(replyMessageManager.getScene());
            editProfileManager.initializeScreen();
        });

        logoutButton.setOnAction(event -> {
            LoginManager loginManager = new LoginManager(replyMessageManager.getScene());
            loginManager.initializeScreen();
        });

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(replyMessageManager.getScene());
            aboutManager.initializeScreen();
        });

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 2)
            editProfileButton.setVisible(false);

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) != 2)
            manageUsersButton.setVisible(false);
    }


}
