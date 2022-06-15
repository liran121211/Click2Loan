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
public class LoanManagerTest extends ApplicationTest {
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


            // Stage [Loan Page 2]
            loanManager.nextPage(1);
            loader = (FXMLLoader) loanManager.getScene().getUserData();
            ((LoanController) loader.getController()).getTotal_income().setText("127000");
            ((LoanController) loader.getController()).getProperty_value().setText("2100200");
            ((LoanController) loader.getController()).getLoan_amount().setText("2100200");
            ((LoanController) loader.getController()).occupationType().setValue("Sales");
            ((LoanController) loader.getController()).organizationType().setValue("Insurance");
            ((LoanController) loader.getController()).ownCarAge().setText("0");
            ((LoanController) loader.getController()).daysEmployed().setValue(LocalDate.now());
            ((LoanController) loader.getController()).getNextBtn().fire();

            // Stage [Loan Page 3]
            loanManager.nextPage(2);
            loader = (FXMLLoader) loanManager.getScene().getUserData();
            ((LoanController) loader.getController()).familyStatus().setValue("Married");
            ((LoanController) loader.getController()).childrensAmount().setText("1");
            ((LoanController) loader.getController()).familyMembers().setText("2");
            ((LoanController) loader.getController()).educationLevel().setValue("Higher Education");
            ((LoanController) loader.getController()).homePhone().setText("054-5342124");
            ((LoanController) loader.getController()).workPhone().setText("054-5342124");
            ((LoanController) loader.getController()).mobilePhone().setText("054-5342124");
            ((LoanController) loader.getController()).getEmail().setText("sce@sce.ac.il");
            ((LoanController) loader.getController()).getNextBtn().fire();

            // Stage [Loan Page 4]
            loanManager.nextPage(3);
            loader = (FXMLLoader) loanManager.getScene().getUserData();
            ((LoanController) loader.getController()).getDoc_btn_0().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_1().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_2().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_3().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_4().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_5().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_6().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_7().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_8().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_9().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_10().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_11().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_12().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_13().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_14().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_15().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_16().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_17().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_18().setSelected(false);
            ((LoanController) loader.getController()).getDoc_btn_19().setSelected(false);
            ((LoanController) loader.getController()).getNextBtn().fire();

            // Stage [Loan Page 5]
            loanManager.nextPage(4);

        });
    }
}