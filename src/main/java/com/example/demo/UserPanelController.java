package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;


/**
 * Controls the main application screen
 */
public class UserPanelController implements PropertyChangeListener {
    @FXML
    private Label accountLabel, balanceLabel;
    @FXML
    private MenuItem withdraw, complaint, logoutButton, editProfileButton, contactusButton, aboutButton, modifyAccountButton, homeButton, myLoansButton, requestLoanButton;
    @FXML
    private Label new_messages_quantity, message_icon;

    private Timeline msg_flasher;
    private Boolean isUserNotified = false;


    public void initManager(UserPanelManager userPanelManager) throws SQLException {
        logoutButton.setOnAction(actionEvent -> userPanelManager.goLogin());
        editProfileButton.setOnAction(event -> userPanelManager.edit());
        contactusButton.setOnAction(event -> userPanelManager.sendMessageBanker());
        complaint.setOnAction(event -> userPanelManager.goComplaint());
        withdraw.setOnAction(event -> userPanelManager.Withdraw());

        message_icon.setOnMouseClicked(event -> {
            MessagesPanelManager manageMessagesManager = new MessagesPanelManager(userPanelManager.getScene());
            manageMessagesManager.initializeScreen();
        });

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(userPanelManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        homeButton.setOnAction(event -> userPanelManager.initializeScreen());

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(userPanelManager.getScene());
            aboutManager.initializeScreen();
        });

        myLoansButton.setOnAction(event -> {
            ClientLoansManager clientLoansManager = new ClientLoansManager(userPanelManager.getScene());
            clientLoansManager.initializeScreen();
        });

        requestLoanButton.setOnAction(event -> {
            LoanApp.isClientRequestedAnotherLoan = true;
            LoanManager loanManager = new LoanManager(userPanelManager.getScene());
            loanManager.initializeScreen();
        });

        // new message icon blinker
        msg_flasher = new Timeline(
                new KeyFrame(Duration.seconds(0.8), e -> message_icon.setVisible(false)),
                new KeyFrame(Duration.seconds(1.6), e -> message_icon.setVisible(true)));
        msg_flasher.setCycleCount(Animation.INDEFINITE);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().compareTo("NEW_MESSAGE") == 0) { // if new message arrived
            if (isUserNotified) // if mailbox still blinking running
                new_messages_quantity.setText("(" + evt.getNewValue() + ")");
            else {
                msg_flasher.play();
                isUserNotified = true;
                new_messages_quantity.setText("(" + evt.getNewValue() + ")");
            }
        }

        if (evt.getPropertyName().compareTo("NO_NEW_MESSAGES") == 0) { // if no new message arrived
            msg_flasher.stop();
            isUserNotified = false;
            new_messages_quantity.setText("(" + evt.getNewValue() + ")");
            message_icon.setVisible(true);
        }
    }

    public Label getAccountLabel() {
        return accountLabel;
    }

    public Label getBalanceLabel() {
        return balanceLabel;
    }

    public MenuItem getRequestLoanButton() {
        return requestLoanButton;
    }
}