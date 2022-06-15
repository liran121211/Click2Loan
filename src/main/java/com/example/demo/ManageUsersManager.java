package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageUsersManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;
    int role = LoginManager.logged_in_user.getInt("role", -1);

    public ManageUsersManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manageUsers.fxml"));
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);
            ManageUsersController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(TableView<ClientsModel> users_list) {
        if (users_list.getSelectionModel().getSelectedItem() != null) {
            if (confirmDeletion(users_list.getSelectionModel().getSelectedItem().getUsername())) {
                LoanApp.sql.delete("users", String.format("id=%s", users_list.getSelectionModel().getSelectedItem().getId()));
                users_list.getItems().remove(users_list.getSelectionModel().getSelectedItem());
            }
        }
    }

    public void commitChange(TableView<ClientsModel> users_list, TableColumn.CellEditEvent<ClientsModel, String> modified_data) {
        if (users_list.getSelectionModel().getSelectedItem() != null) { // if row selected

            if (modified_data.getNewValue().compareTo(modified_data.getOldValue()) != 0) { // if value did change
                int user_id = users_list.getSelectionModel().getSelectedItem().getId();
                String user_role = users_list.getSelectionModel().getSelectedItem().getRole();
                if (users_list.getFocusModel().getFocusedCell().getColumn() == 1) //if Username column selected
                    LoanApp.sql.update("users", "username", String.format("%s", modified_data.getNewValue()), String.format("id=%s", user_id));
                if (users_list.getFocusModel().getFocusedCell().getColumn() == 2) //if Email column selected
                    LoanApp.sql.update("users", "email", String.format("%s", modified_data.getNewValue()), String.format("id=%s", user_id));
                if (users_list.getFocusModel().getFocusedCell().getColumn() == 4) { //if Role column selected
                    if (modified_data.getNewValue().compareTo("Manager") == 0)
                        LoanApp.sql.update("users", "role", String.format("%s", 2), String.format("id=%s", user_id));
                    else if (modified_data.getNewValue().compareTo("Banker") == 0)
                        LoanApp.sql.update("users", "role", String.format("%s", 1), String.format("id=%s", user_id));
                    else if (modified_data.getNewValue().compareTo("Client") == 0)
                        LoanApp.sql.update("users", "role", String.format("%s", 0), String.format("id=%s", user_id));
                    else
                        LoanApp.sql.update("users", "role", String.format("%s", user_role), String.format("id=%s", user_id));

                }
            }
        }
    }

    /**
     * Filter list of users by defined criteria
     *
     * @param clients_list     (TableView<ClientsModel> object)
     * @param search_criterion (TextField object)
     * @param c                (ObservableList<ClientsModel> object)
     * @param filter_by_items  (ComboBox<String> object)
     */
    protected void filterUsers(TableView<ClientsModel> clients_list, TextField search_criterion, ObservableList<ClientsModel> c, ComboBox<String> filter_by_items) {
        String keyword = search_criterion.getText().toLowerCase();
        if (keyword.equals("")) {
            clients_list.getItems().removeAll();
            clients_list.setItems(c);
        } else {
            ObservableList<ClientsModel> filteredData = FXCollections.observableArrayList();
            for (ClientsModel client : clients_list.getItems()) {
                if (filter_by_items.getSelectionModel().getSelectedItem().compareTo("Username") == 0) // search by username
                    if (client.getUsername().toLowerCase().contains(keyword))
                        filteredData.add(client);
                if (filter_by_items.getSelectionModel().getSelectedItem().compareTo("Email") == 0) // search by email
                    if (client.getEmail().toLowerCase().contains(keyword))
                        filteredData.add(client);
                if (filter_by_items.getSelectionModel().getSelectedItem().compareTo("Role") == 0) //search by role
                    if (client.getRole().toLowerCase().contains(keyword))
                        filteredData.add(client);
            }
            clients_list.setItems(filteredData);
        }
    }

    private Boolean confirmDeletion(String username) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete #" + username + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }


    public void goBack() {
        if (role == 0) {
            UserPanelManager userPanelManager = new UserPanelManager(scene);
            userPanelManager.initializeScreen();
        } else if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }

    }

    public void logOut() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
    }

    public void editProfile() {
        ModifyAccountManager modifyAccountManager = new ModifyAccountManager(scene);
        modifyAccountManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}
