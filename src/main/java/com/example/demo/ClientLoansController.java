package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ClientLoansController implements PropertyChangeListener, Initializable {
    @FXML
    private MenuItem logoutButton, editProfileButton, aboutButton, modifyAccountButton, homeButton, requestLoanButton, myLoansButton, contactusButton;
    @FXML
    private Label new_messages_quantity, message_icon;
    @FXML
    private TableView<LoansModel> loans_table;
    @FXML
    private TableColumn<LoansModel, Integer> reference_id_col;
    @FXML
    private TableColumn<LoansModel, String> request_date_col, status_col;
    @FXML
    private TableColumn<LoansModel, Double> loan_amount_col, remaining_amount_col;
    private Timeline msg_flasher;
    private Boolean isUserNotified = false;
    protected final ObservableList<LoansModel> loans_list = FXCollections.observableArrayList();


    public void initManager(ClientLoansManager clientLoansManager) throws SQLException {
        controlsConfiguration(clientLoansManager);
    }

    private void controlsConfiguration(ClientLoansManager clientLoansManager) {
        logoutButton.setOnAction(actionEvent -> clientLoansManager.goLogin());
        editProfileButton.setOnAction(event -> clientLoansManager.edit());
        message_icon.setOnMouseClicked(event -> clientLoansManager.manageMessages());

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(clientLoansManager.getScene());
            aboutManager.initializeScreen();
        });

        homeButton.setOnAction(event -> {
            UserPanelManager userPanelManager = new UserPanelManager(clientLoansManager.getScene());
            userPanelManager.initializeScreen();
        });

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(clientLoansManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(clientLoansManager.getScene());
            aboutManager.initializeScreen();
        });

        myLoansButton.setOnAction(event -> clientLoansManager.initializeScreen());

        requestLoanButton.setOnAction(event -> {
            LoanApp.isClientRequestedAnotherLoan = true;
            LoanManager loanManager = new LoanManager(clientLoansManager.getScene());
            loanManager.initializeScreen();
        });

        contactusButton.setOnAction(event -> {
            SendMessageManager sendMessageManager = new SendMessageManager(clientLoansManager.getScene());
            sendMessageManager.initializeScreen();
        });

        // sort columns
        remaining_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        request_date_col.setSortType(TableColumn.SortType.DESCENDING);
        loan_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        remaining_amount_col.setSortType(TableColumn.SortType.DESCENDING);
        status_col.setSortType(TableColumn.SortType.DESCENDING);

        loans_table.setRowFactory(
                tableView -> {
                    final TableRow<LoansModel> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    MenuItem inspectItem = new MenuItem("Inspect Loan");
                    inspectItem.setOnAction(event -> clientLoansManager.inspectLoan());
                    rowMenu.getItems().addAll(inspectItem);

                    // only display context menu for non-empty rows:
                    row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(rowMenu));
                    return row;
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

    /**
     * Fill table with messages data
     *
     * @return ObservableList (MessageModel).
     */
    protected ObservableList<LoansModel> itemsToTable() {
        try {
            String[][] loan_form_data = LoanApp.sql.select("loan_form_data", "*", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
            for (String[] loan_form : loan_form_data) {
                String[][] loan = LoanApp.sql.select("loans", "*", String.format("id=%s", loan_form[0]));
                loans_list.add(new LoansModel(Integer.parseInt(loan[0][0]), loan_form[33], loan_form[25] + " " + loan_form[26], loan[0][2], Double.parseDouble(loan[0][3]), Double.parseDouble(loan[0][4]), loan[0][5]));
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
        request_date_col.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        loan_amount_col.setCellValueFactory(new PropertyValueFactory<>("loan_amount"));
        remaining_amount_col.setCellValueFactory(new PropertyValueFactory<>("remaining_amount"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        loans_table.setItems(itemsToTable());
    }

    public MenuItem getLogoutButton() {
        return logoutButton;
    }

    public MenuItem getEditProfileButton() {
        return editProfileButton;
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

    public TableView<LoansModel> getLoans_list() {
        return loans_table;
    }


}
