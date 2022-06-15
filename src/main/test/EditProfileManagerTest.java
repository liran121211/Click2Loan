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
public class EditProfileManagerTest extends ApplicationTest {

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
        });
    }

    @Ignore
    public void A1_testInitializeScreen() {
        ////
        // TEST USER EDIT PROFILE PANEL
        ////
        // Stage [Login]
        Platform.setImplicitExit(false);
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
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

            // Stage [EditProfilePanel]
            EditProfileManager editProfileManager = new EditProfileManager(scene);
            editProfileManager.initializeScreen();
            loader = (FXMLLoader) editProfileManager.getScene().getUserData();

            assertNotEquals(((EditProfileController) loader.getController()).getFirstname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getLastname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getBank_number().getText(), "");

            ////
            // TEST BANKER EDIT PROFILE PANEL
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

            // Stage [EditProfilePanel]
            editProfileManager = new EditProfileManager(scene);
            editProfileManager.initializeScreen();
            loader = (FXMLLoader) editProfileManager.getScene().getUserData();

            assertNotEquals(((EditProfileController) loader.getController()).getFirstname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getLastname().getText(), "");
        });
    }


    @Ignore
    public void A2_updateFormData() {
        ////
        // TEST USER EDIT PROFILE PANEL
        ////
        // Stage [Login]
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
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

            // Stage [EditProfilePanel]
            EditProfileManager editProfileManager = new EditProfileManager(scene);
            editProfileManager.initializeScreen();
            loader = (FXMLLoader) editProfileManager.getScene().getUserData();

            assertNotEquals(((EditProfileController) loader.getController()).getFirstname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getLastname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getBank_number().getText(), "");

            String original_first_name = ((EditProfileController) loader.getController()).getFirstname().getText();
            String original_last_name = ((EditProfileController) loader.getController()).getLastname().getText();

            if (original_first_name.contains("#")) { // add # just for test
                ((EditProfileController) loader.getController()).getFirstname().setText(original_first_name.replace("#", ""));
                editProfileManager.updateFormData(loader.getController());
                ((EditProfileController) loader.getController()).getFirstname().setText(original_first_name + "#");
                editProfileManager.updateFormData(loader.getController());
            }

            if (original_last_name.contains("#")) { // add # just for test
                ((EditProfileController) loader.getController()).getLastname().setText(original_last_name.replace("#", ""));
                editProfileManager.updateFormData(loader.getController());
                ((EditProfileController) loader.getController()).getLastname().setText(original_last_name + "#");
                editProfileManager.updateFormData(loader.getController());
            }


            ////
            // TEST BANKER EDIT PROFILE PANEL
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

            // Stage [EditProfilePanel]
            editProfileManager = new EditProfileManager(scene);
            editProfileManager.initializeScreen();
            loader = (FXMLLoader) editProfileManager.getScene().getUserData();

            assertNotEquals(((EditProfileController) loader.getController()).getFirstname().getText(), "");
            assertNotEquals(((EditProfileController) loader.getController()).getLastname().getText(), "");

            original_first_name = ((EditProfileController) loader.getController()).getFirstname().getText();
            original_last_name = ((EditProfileController) loader.getController()).getLastname().getText();

            if (original_first_name.contains("#")) { // add # just for test
                ((EditProfileController) loader.getController()).getFirstname().setText(original_first_name.replace("#", ""));
                editProfileManager.updateFormData(loader.getController());
                ((EditProfileController) loader.getController()).getFirstname().setText(original_first_name + "#");
                editProfileManager.updateFormData(loader.getController());
            }

            if (original_last_name.contains("#")) { // add # just for test
                ((EditProfileController) loader.getController()).getLastname().setText(original_last_name.replace("#", ""));
                editProfileManager.updateFormData(loader.getController());
                ((EditProfileController) loader.getController()).getLastname().setText(original_last_name + "#");
                editProfileManager.updateFormData(loader.getController());
            }


        });
    }

}