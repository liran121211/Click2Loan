package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {
    protected final ObservableList<ClientsModel> observable_list = FXCollections.observableArrayList();

    @FXML
    private Button delete_client, reset_password;
    @FXML
    TextField search_criterion;
    @FXML
    private TableView<ClientsModel> users_list;
    @FXML
    private TableColumn<ClientsModel, String> username_col, email_col, role_col, last_logged_in_col;
    @FXML
    private TableColumn<ClientsModel, Integer> id_col;
    @FXML
    private ComboBox<String> sort_list;
    @FXML
    private MenuItem modifyAccountButton, logoutButton, homeButton, manageUsersButton;

    public void initManager(ManageUsersManager manageUsersManager) {
        controlsConfiguration(manageUsersManager);
    }

    private void controlsConfiguration(ManageUsersManager manageUsersManager) {
        //menu item buttons
        homeButton.setOnAction(event -> manageUsersManager.goBack());
        modifyAccountButton.setOnAction(event -> manageUsersManager.editProfile());
        logoutButton.setOnAction(event -> manageUsersManager.logOut());
        manageUsersButton.setOnAction(event -> manageUsersManager.initializeScreen());
        ////

        sort_list.setItems(FXCollections.observableArrayList("Username", "Email", "Role"));
        delete_client.setOnAction(event -> manageUsersManager.deleteUser(users_list));
        sort_list.getSelectionModel().select(0);

        //make table editable
        users_list.setEditable(true);
        username_col.setEditable(true);
        email_col.setEditable(true);
        role_col.setEditable(true);
        username_col.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        email_col.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        role_col.setCellFactory(ChoiceBoxTableCell.forTableColumn("Client", "Banker", "Manager"));

        username_col.setOnEditCommit(event -> {
            manageUsersManager.commitChange(users_list, event);
            users_list.getItems().removeAll(users_list.getItems());
            users_list.getSelectionModel().clearSelection();
            users_list.setItems(itemsToTable());
        });

        email_col.setOnEditCommit(event -> {
            manageUsersManager.commitChange(users_list, event);
            users_list.getItems().removeAll(users_list.getItems());
            users_list.getSelectionModel().clearSelection();
            users_list.setItems(itemsToTable());
        });

        role_col.setOnEditCommit(event -> {
            manageUsersManager.commitChange(users_list, event);
            users_list.getItems().removeAll(users_list.getItems());
            users_list.getSelectionModel().clearSelection();
            users_list.setItems(itemsToTable());
        });

        // search by criteria
        search_criterion.textProperty().addListener(event -> manageUsersManager.filterUsers(users_list, search_criterion, observable_list, sort_list));
    }

    /**
     * Fill table with messages data
     *
     * @return ObservableList (MessageModel).
     */
    protected ObservableList<ClientsModel> itemsToTable() {
        try {
            String[][] users = LoanApp.sql.select("users", "*");
            for (String[] col : users) {
                if (col[4].compareTo("0") == 0)
                    observable_list.add(new ClientsModel(Integer.parseInt(col[0]), col[1], col[3], col[5], "Client"));
                if (col[4].compareTo("1") == 0)
                    observable_list.add(new ClientsModel(Integer.parseInt(col[0]), col[1], col[3], col[5], "Banker"));
                if (col[4].compareTo("2") == 0)
                    observable_list.add(new ClientsModel(Integer.parseInt(col[0]), col[1], col[3], col[5], "Manager"));
            }
            return observable_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        last_logged_in_col.setCellValueFactory(new PropertyValueFactory<>("last_logged_in"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));
        users_list.setItems(itemsToTable());
    }

    public TableView<ClientsModel> getUsers_list() {
        return users_list;
    }

    public TextField getSearch_criterion() {
        return search_criterion;
    }
}
