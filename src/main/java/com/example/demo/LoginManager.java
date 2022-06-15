package com.example.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;

import java.util.prefs.Preferences;

import static com.example.demo.LoanApp.sql;


/**
 * Manages control flow for logins
 */
public class LoginManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 366;
    private final static int WINDOW_HEIGHT = 436;

    protected final static Preferences logged_in_user = Preferences.userRoot().node("AUTHORIZED_USER");

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("login.fxml")
            );
            scene.setRoot(loader.load());
            this.scene.getWindow().setWidth(WINDOW_WIDTH);
            this.scene.getWindow().setHeight(WINDOW_HEIGHT);
            this.scene.setUserData(loader);
            LoanApp.isClientRequestedAnotherLoan = false;
            LoanApp.isBankerPendingMessageSeen = false;

            LoginController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * show user panel
     */
    public void userPanel() {
        UserPanelManager userPanelManager = new UserPanelManager(scene);
        userPanelManager.initializeScreen();
    }

    public void bankerPanel() {
        BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
        bankerPanelManager.initializeScreen();
    }

    public void ManagerPanel() {
        ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
        managerPanelManager.initializeScreen();
    }

    /**
     * back button to go back to welcome screen
     */
    public void goWelcome() {
        WelcomeManager welcomeManager = new WelcomeManager(scene);
        welcomeManager.initializeScreen();
    }

    /**
     * Check authorization.
     */
    public int authorize(String user, String pass) throws SQLException {

        String[][] fetch = sql.select("users", "*", String.format("username='%s',password='%s'", user, pass));
        if (fetch.length == 0)
            return LoanApp.USER_NOT_EXIST;

        //Update login timestamp
        LocalDate current_time = LocalDate.now();
        sql.update("users", "last_logged_in", String.valueOf(current_time), String.format("id=%s", fetch[0][0]));

        logged_in_user.putInt("userid", Integer.parseInt(fetch[0][0])); // keep id in userPreferences
        logged_in_user.put("username", fetch[0][1]);
        logged_in_user.put("password", fetch[0][2]);
        logged_in_user.put("email", fetch[0][3]);
        logged_in_user.putInt("role", Integer.parseInt(fetch[0][4]));
        logged_in_user.put("last_login", fetch[0][5]);

        if (logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 0) {
            userPanel();
            return 0;
        } else if (logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 1) {
            bankerPanel();
            return 0;
        } else if (logged_in_user.getInt("role", LoanApp.USER_NOT_EXIST) == 2) {
            ManagerPanel();
            return 0;
        } else
            return LoanApp.USER_NOT_EXIST;
    }

    /**
     * forgot password
     */
    public void forgotPassword() {
        ForgotPasswordManager forgotPasswordManager = new ForgotPasswordManager(scene);
        forgotPasswordManager.initializeScreen();
    }

    public Scene getScene() {
        return scene;
    }
}