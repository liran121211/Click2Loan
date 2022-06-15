package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TodoListController implements Initializable {
    private final ObservableList<TodoListModel> observable_list = FXCollections.observableArrayList();

    @FXML
    private Button add_item, delete_item;
    @FXML
    private TableView<TodoListModel> todo_list;
    @FXML
    private TableColumn<TodoListModel, String> to_do_col, status_col;
    @FXML
    MenuItem homeButton, manageUsersButton, editProfileButton, modifyAccountButton, logoutButton, viewButton, aboutButton;

    public void initManager(TodoListManager todoListManager) {
        controlsConfiguration(todoListManager);
    }

    private void controlsConfiguration(TodoListManager todoListManager) {
        add_item.setOnAction(event -> todoListManager.addItem(todo_list));
        delete_item.setOnAction(event -> todoListManager.deleteItem(todo_list));

        //make table editable
        to_do_col.setEditable(true);
        todo_list.setEditable(true);
        to_do_col.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        status_col.setCellFactory(ChoiceBoxTableCell.forTableColumn("NEW", "PENDING", "DONE"));
        to_do_col.setOnEditCommit(event -> {
            todoListManager.commitChange(todo_list, event);
            todo_list.getItems().removeAll(todo_list.getItems());
            todo_list.getSelectionModel().clearSelection();
            todo_list.setItems(itemsToTable());

        });
        status_col.setOnEditCommit(event -> {
            todoListManager.commitChange(todo_list, event);
            todo_list.getItems().removeAll(todo_list.getItems());
            todo_list.getSelectionModel().clearSelection();
            todo_list.setItems(itemsToTable());

        });

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(todoListManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        homeButton.setOnAction(event -> todoListManager.goBack());

        viewButton.setOnAction(event -> todoListManager.initializeScreen());

        manageUsersButton.setOnAction(event -> {
            ManageUsersManager manageUsersManager = new ManageUsersManager(todoListManager.getScene());
            manageUsersManager.initializeScreen();
        });

        editProfileButton.setOnAction(event -> {
            EditProfileManager editProfileManager = new EditProfileManager(todoListManager.getScene());
            editProfileManager.initializeScreen();
        });

        logoutButton.setOnAction(event -> {
            LoginManager loginManager = new LoginManager(todoListManager.getScene());
            loginManager.initializeScreen();
        });

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(todoListManager.getScene());
            aboutManager.initializeScreen();
        });


        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 2)
            editProfileButton.setVisible(false);

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) != 2)
            manageUsersButton.setVisible(false);
    }

    /**
     * Fill table with messages data
     *
     * @return ObservableList (MessageModel).
     */
    private ObservableList<TodoListModel> itemsToTable() {
        try {
            String[][] items = LoanApp.sql.select("todolist", "*", String.format("user_id=%s", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST)));
            for (String[] item : items)
                observable_list.add(new TodoListModel(Integer.parseInt(item[0]), Integer.parseInt(item[1]), item[2], item[3]));
            return observable_list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        to_do_col.setCellValueFactory(new PropertyValueFactory<>("Item"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("Status"));
        todo_list.setItems(itemsToTable());
    }

    public Button getAdd_item() {
        return add_item;
    }

    public Button getDelete_item() {
        return delete_item;
    }

    public TableView<TodoListModel> getTodo_list() {
        return todo_list;
    }
}
