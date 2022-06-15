package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TodoListModel {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty user_id;
    private final SimpleStringProperty item;
    private final SimpleStringProperty status;

    public TodoListModel(int id, int user_id, String item, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.user_id = new SimpleIntegerProperty(user_id);
        this.item = new SimpleStringProperty(item);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public int getUser_id() {
        return user_id.get();
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String item) {
        this.item.set(item);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}