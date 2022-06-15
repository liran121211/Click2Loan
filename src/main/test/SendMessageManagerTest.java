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
public class SendMessageManagerTest extends ApplicationTest {

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
        });
    }

    @Ignore
    public void A1_initializeScreen() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [SendMessagePanel]
            SendMessageManager sendMessageManager = new SendMessageManager(scene);
            sendMessageManager.initializeScreen();
            loader = (FXMLLoader) sendMessageManager.getScene().getUserData();

            assertNotNull(((SendMessageController) loader.getController()).getBankers_list());
            assertNotNull(((SendMessageController) loader.getController()).getSend_message());
            assertNotNull(((SendMessageController) loader.getController()).getMessage_subject());
            assertNotNull(((SendMessageController) loader.getController()).getMessage_body());
            assertNotEquals(((SendMessageController) loader.getController()).getBankers_list().getItems().get(0), "");
        });
    }

    @Ignore
    public void A2_sendMessage() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [SendMessagePanel]
            SendMessageManager sendMessageManager = new SendMessageManager(scene);
            sendMessageManager.initializeScreen();
            loader = (FXMLLoader) sendMessageManager.getScene().getUserData();

            ((SendMessageController) loader.getController()).getMessage_subject().setText("TEST SUBJECT");
            ((SendMessageController) loader.getController()).getMessage_body().setText("TEST MESSAGE");
            ((SendMessageController) loader.getController()).getSend_message().fire();
        });
    }
}