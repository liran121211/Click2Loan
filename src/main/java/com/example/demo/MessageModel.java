package com.example.demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MessageModel {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty message;
    private final SimpleStringProperty sender;
    private final SimpleStringProperty time;

    public MessageModel(int id, String subject, String message, String time, String sender) {
        this.id = new SimpleIntegerProperty(id);
        this.subject = new SimpleStringProperty(subject);
        this.message = new SimpleStringProperty(message);
        this.time = new SimpleStringProperty(time);
        this.sender = new SimpleStringProperty(sender);
    }

    public String getSubject() {
        return subject.get();
    }

    public String getMessage() {
        return message.get();
    }


    public void setMessage(String message) {
        this.message.set(message);
    }

    public String getTime() {
        return time.get();
    }


    public void setTime(String time) {
        this.time.set(time);
    }

    public String getSender() {
        return sender.get();
    }

    public int getID() {
        return id.get();
    }


}