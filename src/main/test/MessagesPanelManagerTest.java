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
public class MessagesPanelManagerTest extends ApplicationTest {

    private Scene scene;
    private FXMLLoader loader;
    private static final PostgreSQL sql = PostgreSQL.getInstance();
    private final String TEST_MESSAGE_IDENTIFIER = "TEST MESSAGE PLEASE IGNORE #9801456456";

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
            ((LoginController) loader.getController()).getUser().setText("tamaram");
            ((LoginController) loader.getController()).getPassword().setText("PM2022");
            ((LoginController) loader.getController()).getLoginButton().fire();


            // Stage [ManagerPanel]
            ManagerPanelManager managerPanelManager = new ManagerPanelManager(scene);
            managerPanelManager.initializeScreen();
            loader = (FXMLLoader) managerPanelManager.getScene().getUserData();
        });

    }

    @Ignore
    public void A1_testInitializeScreen() {
        // Stage [MessagePanel]
        Platform.runLater(() -> {
            MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
            messagesPanelManager.initializeScreen();
            loader = (FXMLLoader) messagesPanelManager.getScene().getUserData();
            assertNotNull(((MessagesPanelController) loader.getController()).messages_list);

            assertNotEquals(((MessagesPanelController) loader.getController()).messages_list.getItems().get(0).getMessage(), "");
            assertNotEquals(((MessagesPanelController) loader.getController()).messages_list.getItems().get(0).getSubject(), "");
            assertNotEquals(((MessagesPanelController) loader.getController()).messages_list.getItems().get(0).getTime(), "");
        });
    }

    @Ignore
    public void A2_testViewMessage() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [MessagePanel]
            MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
            messagesPanelManager.initializeScreen();
            loader = (FXMLLoader) messagesPanelManager.getScene().getUserData();
            assertNotNull(((MessagesPanelController) loader.getController()).messages_list);

            assertNotNull((((MessagesPanelController) loader.getController()).messages_list));
            assertNotNull((((MessagesPanelController) loader.getController()).messages_list).getSelectionModel());
            ViewMessageManager viewMessageManager = new ViewMessageManager(scene);
            viewMessageManager.initializeScreen((((MessagesPanelController) loader.getController()).messages_list).getItems().get(0));
        });
    }

    @Ignore
    public void A3_testReplyMessage() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [MessagePanel]
            MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
            messagesPanelManager.initializeScreen();
            loader = (FXMLLoader) messagesPanelManager.getScene().getUserData();
            assertNotNull(((MessagesPanelController) loader.getController()).messages_list);

            assertNotNull((((MessagesPanelController) loader.getController()).messages_list));
            assertNotNull((((MessagesPanelController) loader.getController()).messages_list).getSelectionModel());

            ReplyMessageManager replyMessageManager = new ReplyMessageManager(scene);
            replyMessageManager.initializeScreen((((MessagesPanelController) loader.getController()).messages_list).getItems().get(0));
            loader = (FXMLLoader) replyMessageManager.getScene().getUserData();
            ((ReplyMessageController) loader.getController()).message_subject.setText(TEST_MESSAGE_IDENTIFIER);
            ((ReplyMessageController) loader.getController()).message_subject.setText(TEST_MESSAGE_IDENTIFIER);
            ((ReplyMessageController) loader.getController()).send_message.fire();
        });
    }

    @Ignore
    public void A4_testDeleteMessage() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [MessagePanel]
            MessagesPanelManager messagesPanelManager = new MessagesPanelManager(scene);
            messagesPanelManager.initializeScreen();
            loader = (FXMLLoader) messagesPanelManager.getScene().getUserData();
            assertNotNull(((MessagesPanelController) loader.getController()).messages_list);
            assertNotNull(((MessagesPanelController) loader.getController()).delete_msg);
        });
    }

}