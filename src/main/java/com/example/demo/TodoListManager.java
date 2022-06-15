package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TodoListManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 665;
    private final static int WINDOW_HEIGHT = 690;
    public final static int BRAND_NEW_NOTE = -1;

    public TodoListManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("todoList.fxml")
            );
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            TodoListController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void deleteItem(TableView<TodoListModel> items_list) {
        if (items_list.getSelectionModel().getSelectedItem() != null) {
            LoanApp.sql.delete("todolist", String.format("id=%s", items_list.getSelectionModel().getSelectedItem().getId()));
            items_list.getItems().remove(items_list.getSelectionModel().getSelectedItem());
        }
    }

    public void commitChange(TableView<TodoListModel> items_list, TableColumn.CellEditEvent<TodoListModel, String> modified_data) {
        if (items_list.getSelectionModel().getSelectedItem() != null) { // if row selected

            if (modified_data.getNewValue().compareTo(modified_data.getOldValue()) != 0) { // if value did change
                int note_id = items_list.getSelectionModel().getSelectedItem().getId();
                String item_data = items_list.getSelectionModel().getSelectedItem().getItem();
                String item_status = items_list.getSelectionModel().getSelectedItem().getStatus();
                String columns = "user_id, item, status";

                if (items_list.getFocusModel().getFocusedCell().getColumn() == 0) { //if To-do column selected
                    if (note_id == BRAND_NEW_NOTE) // if new note inserted
                        LoanApp.sql.insert("todolist", String.format("%s", columns), String.format("%s, '%s', '%s'", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST), modified_data.getNewValue(), item_status));
                    else
                        LoanApp.sql.update("todolist", "item", String.format("%s", modified_data.getNewValue()), String.format("id=%s", note_id));
                }

                if (items_list.getFocusModel().getFocusedCell().getColumn() == 1) { //if Status column selected
                    if (note_id == BRAND_NEW_NOTE) // if new note inserted
                        LoanApp.sql.insert("todolist", String.format("%s", columns), String.format("%s, '%s', '%s'", LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST), item_data, modified_data.getNewValue()));
                    else
                        LoanApp.sql.update("todolist", "status", String.format("%s", modified_data.getNewValue()), String.format("id=%s", note_id));
                }

            }
        }
    }

    public void addItem(TableView<TodoListModel> items_list) {
        TodoListModel item = new TodoListModel(BRAND_NEW_NOTE, LoginManager.logged_in_user.getInt("userid", LoanApp.USER_NOT_EXIST), "Write Your Task Here...", "NEW");
        items_list.getItems().add(item);

    }

    protected void goBack() {
        int role = LoginManager.logged_in_user.getInt("role", -1);
        if (role == 1) {
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
        } else if (role == 2) {
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
