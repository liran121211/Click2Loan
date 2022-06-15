package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class LoansGraphController {
    @FXML
    MenuItem homeButton, editProfileButton, modifyAccountButton, logoutButton, aboutButton;
    @FXML
    PieChart pieChart;
    @FXML
    VBox vBox;


    public void initManager(LoansGraphManager loansGraphManager) throws SQLException {
        loadPieChart(); // load data into chart

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(loansGraphManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        homeButton.setOnAction(event -> loansGraphManager.goBack());


        editProfileButton.setOnAction(event -> {
            EditProfileManager editProfileManager = new EditProfileManager(loansGraphManager.getScene());
            editProfileManager.initializeScreen();
        });

        logoutButton.setOnAction(event -> {
            LoginManager loginManager = new LoginManager(loansGraphManager.getScene());
            loginManager.initializeScreen();
        });

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 2)
            editProfileButton.setVisible(false);

    }

    private void loadPieChart() {
        try {
            String[][] loans = LoanApp.sql.select("loans", "status");
            int count_approved = 0, count_pending = 0, count_rejected = 0;
            for (String[] status : loans) {
                if (status[0].compareTo("APPROVED") == 0)
                    count_approved++;
                if (status[0].compareTo("PENDING") == 0)
                    count_pending++;
                if (status[0].compareTo("REJECTED") == 0)
                    count_rejected++;
            }


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(String.format("Rejected (%s)", count_rejected), count_rejected),
                    new PieChart.Data(String.format("Pending (%s)", count_pending), count_pending),
                    new PieChart.Data(String.format("Approved (%s)", count_approved), count_approved)
            );


            //Creating a Pie chart
            pieChart.setData(pieChartData);

            //Setting the title of the Pie chart
            pieChart.setTitle(String.format("Total Loans: %s", (loans.length)));

            //setting the direction to arrange the data
            pieChart.setClockwise(true);

            //Setting the length of the label line
            pieChart.setLabelLineLength(50);

            //Setting the labels of the pie chart visible
            pieChart.setLabelsVisible(true);

            //Setting the start angle of the pie chart
            pieChart.setStartAngle(180);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuItem getHomeButton() {
        return homeButton;
    }

    public MenuItem getEditProfileButton() {
        return editProfileButton;
    }

    public MenuItem getModifyAccountButton() {
        return modifyAccountButton;
    }

    public MenuItem getLogoutButton() {
        return logoutButton;
    }

    public MenuItem getAboutButton() {
        return aboutButton;
    }

}
