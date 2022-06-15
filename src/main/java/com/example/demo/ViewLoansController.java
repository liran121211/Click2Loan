package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ViewLoansController implements Initializable {
    @FXML
    private MenuItem logoutButton, modifyAccountButton, manage_clients, homeButton, aboutButton, loansStatusButton;
    @FXML
    private Label approved_count, rejected_count, pending_count;
    @FXML
    private TableView<LoansModel> loans_table;
    @FXML
    private TableColumn<LoansModel, Integer> reference_id_col;
    @FXML
    private TableColumn<LoansModel, String> client_name_col, request_date_col, status_col;
    @FXML
    private TableColumn<LoansModel, Double> loan_amount_col, remaining_amount_col;
    @FXML
    protected final ObservableList<LoansModel> loans_list = FXCollections.observableArrayList();


    public void initManager(ViewLoansManager viewLoansManager) throws SQLException {
        controlsConfiguration(viewLoansManager);
    }

    private void controlsConfiguration(ViewLoansManager viewLoansManager) {
        logoutButton.setOnAction(actionEvent -> viewLoansManager.goLogin());

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(viewLoansManager.getScene());
            aboutManager.initializeScreen();
        });

        homeButton.setOnAction(event -> {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(viewLoansManager.getScene());
            managerPanelManager.initializeScreen();
        });

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(viewLoansManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(viewLoansManager.getScene());
            aboutManager.initializeScreen();
        });

        manage_clients.setOnAction(event -> {
            ManageUsersManager manageUsersManager = new ManageUsersManager(viewLoansManager.getScene());
            manageUsersManager.initializeScreen();
        });

        loansStatusButton.setOnAction(event -> {LoansGraphManager loansGraphManager = new LoansGraphManager(viewLoansManager.getScene());
            loansGraphManager.initializeScreen();
        });





        // sort columns
        remaining_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        client_name_col.setSortType(TableColumn.SortType.DESCENDING);
        request_date_col.setSortType(TableColumn.SortType.DESCENDING);
        loan_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        remaining_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        status_col.setSortType(TableColumn.SortType.DESCENDING);
    }


    /**
     * Fill table with messages data
     *
     * @return ObservableList (MessageModel).
     */
    protected ObservableList<LoansModel> itemsToTable() {
        try {
            String[][] clients = LoanApp.sql.select("users", "id", "role=0");
            for (String[] col : clients) {
                String[][] loan_form_data = LoanApp.sql.select("loan_form_data", "reference_id", String.format("user_id=%s", col[0]));
                String[][] loan_data = LoanApp.sql.select("loans", "*", String.format("user_id=%s", col[0]));
                String[][] full_name = LoanApp.sql.select("clients", "first_name, last_name", String.format("user_id=%s", col[0]));
                if (loan_form_data.length > 0 && loan_data.length > 0 && full_name.length > 0)
                    loans_list.add(new LoansModel(Integer.parseInt(loan_data[0][0]), loan_form_data[0][0].toUpperCase(), full_name[0][0] + " " + full_name[0][1], loan_data[0][2], Double.parseDouble(loan_data[0][3]), Double.parseDouble(loan_data[0][4]), loan_data[0][5]));
            }
            return loans_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reference_id_col.setCellValueFactory(new PropertyValueFactory<>("reference_id"));
        client_name_col.setCellValueFactory(new PropertyValueFactory<>("client_name"));
        request_date_col.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        loan_amount_col.setCellValueFactory(new PropertyValueFactory<>("loan_amount"));
        remaining_amount_col.setCellValueFactory(new PropertyValueFactory<>("remaining_amount"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        loans_table.setItems(itemsToTable());
    }

    public MenuItem getLogoutButton() {
        return logoutButton;
    }

    public MenuItem getAboutButton() {
        return aboutButton;
    }

    public MenuItem getModifyAccountButton() {
        return modifyAccountButton;
    }

    public MenuItem getHomeButton() {
        return homeButton;
    }

    public Label getApproved_count() {
        return approved_count;
    }

    public Label getRejected_count() {
        return rejected_count;
    }

    public Label getPending_count() {
        return pending_count;
    }
}