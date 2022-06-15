package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


public class ManagerPanelController implements PropertyChangeListener {
    private Boolean isUserNotified = false;
    private Timeline msg_flasher;
    @FXML
    private MenuItem viewLoansButton, logoutButton, modifyAccountButton, manage_clients, homeButton, aboutButton, loansStatusButton;
    @FXML
    private Label new_messages_quantity, message_icon;
    @FXML
    private StackPane stackpane;
    @FXML
    private LineChart<String, Number> line_chart;


    public void initManager(ManagerPanelManager managerPanelManager) throws SQLException {
        controlsConfiguration(managerPanelManager);
    }

    private void controlsConfiguration(ManagerPanelManager managerPanelManager) {
        ////menu buttons
        logoutButton.setOnAction(actionEvent -> managerPanelManager.goLogin());
        modifyAccountButton.setOnAction(event -> managerPanelManager.modifyAccount());
        manage_clients.setOnAction(event -> managerPanelManager.manageClients());
        message_icon.setOnMouseClicked(event -> managerPanelManager.manageMessages());
        homeButton.setOnAction(event -> managerPanelManager.initializeScreen());

        aboutButton.setOnAction(event -> {
            AboutManager aboutManager = new AboutManager(managerPanelManager.getScene());
            aboutManager.initializeScreen();
        });

        viewLoansButton.setOnAction(event -> {
            ViewLoansManager viewLoansManager = new ViewLoansManager(managerPanelManager.getScene());
            viewLoansManager.initializeScreen();
        });

        loansStatusButton.setOnAction(event -> {
            LoansGraphManager loansGraphManager = new LoansGraphManager(managerPanelManager.getScene());
            loansGraphManager.initializeScreen();
        });

        // new message icon blinker
        msg_flasher = new Timeline(
                new KeyFrame(Duration.seconds(0.8), e -> message_icon.setVisible(false)),
                new KeyFrame(Duration.seconds(1.6), e -> message_icon.setVisible(true)));
        msg_flasher.setCycleCount(Animation.INDEFINITE);

        //Line Chart
        NumberAxis xAxis = new NumberAxis();
        line_chart.getYAxis().setLabel("Learning Rate/ Error");
        line_chart.getXAxis().setLabel("Epochs");

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Accuracy");

        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("Loss");

        String[] accuracy = loadChart("accuracy");
        for (int i = 0; i < accuracy.length-10; i++)
            dataSeries1.getData().add(new XYChart.Data<>(String.format("%s", i), Double.parseDouble(accuracy[i])));

        String[] loss = loadChart("loss");
        for (int i = 0; i < accuracy.length-10; i++)
            dataSeries2.getData().add(new XYChart.Data<>(String.format("%s", i), Double.parseDouble(loss[i])));

        line_chart.getData().add(dataSeries1);
        line_chart.getData().add(dataSeries2);
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

    private String[] loadChart(String metric) {
        BufferedReader buffer_reader;
        String current_line;
        int row_cnt = 0;
        String[] data = new String[101];

        try {
            if (metric.compareTo("accuracy")==0){
                buffer_reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\core\\bin\\metrics\\accuracy_logs.csv"));
                buffer_reader.readLine();// IGNORE FIRST ROW

                while ((current_line = buffer_reader.readLine()) != null) {
                    String[] row = current_line.split(",");    // use comma as separator
                    double val = Double.parseDouble(row[1]);
                    data[row_cnt] = String.valueOf(val);
                    row_cnt++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            if (metric.compareTo("loss")==0){
                buffer_reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\core\\bin\\metrics\\loss_logs.csv"));
                buffer_reader.readLine();// IGNORE FIRST ROW

                while ((current_line = buffer_reader.readLine()) != null) {
                    String[] row = current_line.split(",");    // use comma as separator
                    double val = Double.parseDouble(row[1]);
                    data[row_cnt] = String.valueOf(val);
                    row_cnt++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public StackPane getStackpane() {
        return stackpane;
    }
}
