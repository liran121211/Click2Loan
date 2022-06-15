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
public class AboutManagerTest extends ApplicationTest {
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
            //stage.getIcons().add(new Image(String.format("file:%s\\src\\main\\resources\\com\\example\\demo\\img\\app_icon.jpg", System.getProperty("user.dir"))));
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
            // Stage [AboutPanel]
            AboutManager aboutManager = new AboutManager(scene);
            aboutManager.initializeScreen();
            loader = (FXMLLoader) aboutManager.getScene().getUserData();

            assertNotNull(((AboutController) loader.getController()).getLoanAgreementButton());
            assertNotNull(((AboutController) loader.getController()).getUserAgreementButton());
            assertNotNull(((AboutController) loader.getController()).getRegistrationAgreementButton());
            assertNotNull(((AboutController) loader.getController()).getWebLoader());
            ((AboutController) loader.getController()).getLoanAgreementButton().fire();
            ((AboutController) loader.getController()).getUserAgreementButton().fire();
            ((AboutController) loader.getController()).getRegistrationAgreementButton().fire();
        });

    }
}