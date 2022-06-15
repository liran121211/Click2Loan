package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class ClientsModel {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty email;
    private final SimpleStringProperty last_logged_in;
    private final SimpleStringProperty role;

    public ClientsModel(int id, String username, String email, String last_logged_in, String role) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.last_logged_in = new SimpleStringProperty(last_logged_in);
        this.role = new SimpleStringProperty(role);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }


    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getRole() {
        return role.get();
    }


    public void setRole(String role) {
        this.role.set(role);
    }

    public String getLast_logged_in() {
        return last_logged_in.get();
    }

    public SimpleStringProperty last_logged_inProperty() {
        return last_logged_in;
    }
}