package com.example.demo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoansModel {

    private final SimpleIntegerProperty id;
    private final SimpleDoubleProperty loan_amount;
    private final SimpleStringProperty status;
    private final SimpleStringProperty reference_id;
    private final SimpleStringProperty client_name;
    private final SimpleStringProperty request_date;
    private final SimpleDoubleProperty remaining_amount;


    public LoansModel(int id, String reference_id, String client_name, String request_date, double loan_amount, double remaining_amount, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.reference_id = new SimpleStringProperty(reference_id);
        this.client_name = new SimpleStringProperty(client_name);
        this.request_date = new SimpleStringProperty(request_date);
        this.loan_amount = new SimpleDoubleProperty(loan_amount);
        this.remaining_amount = new SimpleDoubleProperty(remaining_amount);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public double getLoan_amount() {
        return loan_amount.get();
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleDoubleProperty loan_amountProperty() {
        return loan_amount;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getReference_id() {
        return reference_id.get();
    }

    public SimpleStringProperty reference_idProperty() {
        return reference_id;
    }

    public String getClient_name() {
        return client_name.get();
    }

    public SimpleStringProperty client_nameProperty() {
        return client_name;
    }

    public String getRequest_date() {
        return request_date.get();
    }

    public SimpleStringProperty request_dateProperty() {
        return request_date;
    }

    public double getRemaining_amount() {
        return remaining_amount.get();
    }

    public SimpleDoubleProperty remaining_amountProperty() {
        return remaining_amount;
    }
}