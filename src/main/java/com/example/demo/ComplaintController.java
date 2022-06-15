package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class ComplaintController implements PropertyChangeListener {
    private final ObservableList<String> complain_list = FXCollections.observableArrayList("Bug", "Banker Report", "General");
    @FXML
    private Button send_message, exit_message;
    @FXML
    private TextField message_subject;
    @FXML
    private TextArea message_body;
    @FXML
    private Label message_sent_popup;
    @FXML
    private ComboBox<String> complain;
    @FXML
    MenuItem logoutButton, editProfileButton, aboutButton, contactusButton, homeButton;


    public void initManager(ComplaintManager complaintManager) throws SQLException {
        complaintManager.getNotifier().addPropertyChangeListener(this);
        complain.setItems(complain_list);
        complain.getSelectionModel().select(0); // set first item of list selected

        message_sent_popup.setVisible(false);//hide success message sent

        logoutButton.setOnAction(event -> {
            LoginManager loginManager = new LoginManager(complaintManager.getScene());
            loginManager.initializeScreen();
        });

        homeButton.setOnAction(event -> {
            UserPanelManager userPanelManager = new UserPanelManager(complaintManager.getScene());
            userPanelManager.initializeScreen();
        });

        editProfileButton.setOnAction(event -> {
            EditProfileManager editProfileManager = new EditProfileManager(complaintManager.getScene());
            editProfileManager.initializeScreen();
        });

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(complaintManager.getScene());
            aboutManager.initializeScreen();
        });

        contactusButton.setOnAction(event -> complaintManager.initializeScreen());
        exit_message.setOnAction(event -> complaintManager.returnToMessages());
        send_message.setOnAction(event -> complaintManager.sendMessage(message_subject.getText(), message_body.getText(), complain.getSelectionModel().getSelectedItem()));


    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().compareTo("MESSAGE") == 0 && evt.getNewValue() instanceof Scene) {
            exit_message.setDisable(true);
            send_message.setDisable(true);
            ((Scene) (evt.getNewValue())).getRoot().setEffect(new BoxBlur(5.0, 5.0, 1));
            message_sent_popup.setVisible(true);
            new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                UserPanelManager userPanelManager = new UserPanelManager((Scene) (evt.getNewValue()));
                userPanelManager.initializeScreen();
            })).play();
        }
    }

    public Button getSend_message() {
        return send_message;
    }

    public TextField getMessage_subject() {
        return message_subject;
    }

    public TextArea getMessage_body() {
        return message_body;
    }

    public ObservableList<String> getComplain_list() {
        return complain_list;
    }

    public ComboBox<String> getComplain() {
        return complain;
    }
}
