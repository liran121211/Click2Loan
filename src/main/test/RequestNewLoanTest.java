import com.example.demo.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import java.time.LocalDate;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequestNewLoanTest extends ApplicationTest {
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
            ((UserPanelController) loader.getController()).getRequestLoanButton().fire();
        });
    }


    @Ignore
    public void A1_initializeScreen() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            // Stage [Loan Page 1]
            LoanManager loanManager = new LoanManager(scene);
            loanManager.initializeScreen();
            loader = (FXMLLoader) loanManager.getScene().getUserData();
            ((LoanController) loader.getController()).getFull_name().setText("Lionel B. Hudgens");
            ((LoanController) loader.getController()).getAddress().setText("2143 Taylor St");
            ((LoanController) loader.getController()).getCity().setText("Haifa");
            ((LoanController) loader.getController()).getCountry().setValue("Israel");
            ((LoanController) loader.getController()).getZipcode().setText("8508651");
            ((LoanController) loader.getController()).getDate_of_birth().setValue(LocalDate.now());
            ((LoanController) loader.getController()).getGender().setValue("Male");
            ((LoanController) loader.getController()).getNextBtn().fire();
        });
    }
}