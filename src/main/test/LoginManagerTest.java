import com.example.demo.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginManagerTest extends ApplicationTest {

    private Scene scene;
    private FXMLLoader loader;
    private static final PostgreSQL sql = PostgreSQL.getInstance();

    @BeforeClass
    public static void initSQL() {
        sql.openConnection();
    }

    @Ignore
    public void start(Stage stage) {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            scene = new Scene(new StackPane());
            WelcomeManager welcomeManager = new WelcomeManager(scene);
            welcomeManager.initializeScreen();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.getIcons().add(new Image(String.format("file:%s\\src\\main\\resources\\com\\example\\demo\\img\\app_icon.jpg", System.getProperty("user.dir"))));
            stage.show();

            // Stage [Welcome]
            loader = (FXMLLoader) welcomeManager.getScene().getUserData();
            ((WelcomeController) loader.getController()).getLogin().fire();
        });
    }

    @Ignore
    public void A1_initializeScreen() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
        loader = (FXMLLoader) loginManager.getScene().getUserData();
        assertNotNull(((LoginController) loader.getController()).getUser());
        assertNotNull(((LoginController) loader.getController()).getPassword());
        assertNotNull(((LoginController) loader.getController()).getLoginButton());
    }

    @Ignore
    public void A2_authorize() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            ////
            // USER LOGIN
            ////
            // Stage [Login]
            LoginManager loginManager = new LoginManager(scene);
            loginManager.initializeScreen();
            loader = (FXMLLoader) loginManager.getScene().getUserData();
            ((LoginController) loader.getController()).getUser().setText("yonatan");
            ((LoginController) loader.getController()).getPassword().setText("1234");
            ((LoginController) loader.getController()).getLoginButton().fire();

            // Stage [UserPanel]
            UserPanelManager userPanelManager = new UserPanelManager(scene);
            userPanelManager.initializeScreen();
            loader = (FXMLLoader) userPanelManager.getScene().getUserData();
            userPanelManager.goLogin();

            ////
            // BANKER LOGIN
            ////
            // Stage [Login]
            loginManager = new LoginManager(scene);
            loginManager.initializeScreen();
            loader = (FXMLLoader) loginManager.getScene().getUserData();
            ((LoginController) loader.getController()).getUser().setText("liransm");
            ((LoginController) loader.getController()).getPassword().setText("PM2022");
            ((LoginController) loader.getController()).getLoginButton().fire();

            // Stage [UserPanel]
            BankerPanelManager bankerPanelManager = new BankerPanelManager(scene);
            bankerPanelManager.initializeScreen();
            loader = (FXMLLoader) bankerPanelManager.getScene().getUserData();
            bankerPanelManager.goLogin();

            ////
            // MANAGER LOGIN
            ////
            loginManager = new LoginManager(scene);
            loginManager.initializeScreen();
            loader = (FXMLLoader) loginManager.getScene().getUserData();
            ((LoginController) loader.getController()).getUser().setText("tamaram");
            ((LoginController) loader.getController()).getPassword().setText("PM2022");
            ((LoginController) loader.getController()).getLoginButton().fire();


            // Stage [UserPanel]
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
            loader = (FXMLLoader) managerPanelManager.getScene().getUserData();
            managerPanelManager.goLogin();
        });
    }
}