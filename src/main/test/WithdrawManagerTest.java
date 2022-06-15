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
public class WithdrawManagerTest extends ApplicationTest {
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
            WithdrawManager withdrawManager = new WithdrawManager(scene);
            withdrawManager.initializeScreen();
            loader = (FXMLLoader) withdrawManager.getScene().getUserData();

            assertNotNull(((WithdrawController) loader.getController()).getAcc_num());
            assertNotNull(((WithdrawController) loader.getController()).getBranch());
            assertNotNull(((WithdrawController) loader.getController()).getAmount());
            assertNotNull(((WithdrawController) loader.getController()).getWithdrawButton());
            assertNotNull(((WithdrawController) loader.getController()).getBank_name());
            assertNotNull(((WithdrawController) loader.getController()).getFull_name());

            ((WithdrawController) loader.getController()).getAcc_num().setText("0");
            ((WithdrawController) loader.getController()).getBranch().setText("0");
           ((WithdrawController) loader.getController()).getAmount().setText("0");
            ((WithdrawController) loader.getController()).getWithdrawButton().setText("0");
            ((WithdrawController) loader.getController()).getBank_name().setText("0");
            ((WithdrawController) loader.getController()).getFull_name().setText("0");
            ((WithdrawController) loader.getController()).getAmount().setText("1");
            ((WithdrawController) loader.getController()).getWithdrawButton().fire();
        });

    }
}