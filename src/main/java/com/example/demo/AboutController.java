package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

import java.sql.SQLException;

public class AboutController {
    @FXML
    Button userAgreementButton, loanAgreementButton, registrationAgreementButton;
    @FXML
    MenuItem homeButton, editProfileButton, modifyAccountButton, logoutButton, aboutButton, myLoansButton, requestLoanButton;
    @FXML
    WebView webLoader;

    public void initManager(AboutManager aboutManager) throws SQLException {

        modifyAccountButton.setOnAction(event -> {
            ModifyAccountManager modifyAccountManager = new ModifyAccountManager(aboutManager.getScene());
            modifyAccountManager.initializeScreen();
        });

        homeButton.setOnAction(event -> aboutManager.goBack());


        editProfileButton.setOnAction(event -> {
            EditProfileManager editProfileManager = new EditProfileManager(aboutManager.getScene());
            editProfileManager.initializeScreen();
        });

        logoutButton.setOnAction(event -> {
            LoginManager loginManager = new LoginManager(aboutManager.getScene());
            loginManager.initializeScreen();
        });

        myLoansButton.setOnAction(event -> {
            ClientLoansManager clientLoansManager = new ClientLoansManager(aboutManager.getScene());
            clientLoansManager.initializeScreen();
        });

        requestLoanButton.setOnAction(event -> {
            LoanApp.isClientRequestedAnotherLoan = true;
            LoanManager loanManager = new LoanManager(aboutManager.getScene());
            loanManager.initializeScreen();
        });

        if (LoginManager.logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 2)
            editProfileButton.setVisible(false);

        userAgreementButton.setOnAction(event -> webLoader.getEngine().loadContent(FormAdapter.USER_AGREEMENT));
        loanAgreementButton.setOnAction(event -> webLoader.getEngine().loadContent(FormAdapter.LOAN_AGREEMENT));
        registrationAgreementButton.setOnAction(event -> webLoader.getEngine().loadContent(FormAdapter.REGISTRATION_AGREEMENT));


    }


    public Button getUserAgreementButton() {
        return userAgreementButton;
    }

    public Button getLoanAgreementButton() {
        return loanAgreementButton;
    }

    public Button getRegistrationAgreementButton() {
        return registrationAgreementButton;
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

    public WebView getWebLoader() {
        return webLoader;
    }
}
