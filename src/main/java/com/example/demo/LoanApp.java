package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Main application class for the login demo application
 */
public class LoanApp extends Application {
    private Scene scene;

    public static PostgreSQL sql = PostgreSQL.getInstance();
    public final static int USER_NOT_EXIST = -999;
    public static Boolean isBankerPendingMessageSeen = false;
    public static Boolean isClientRequestedAnotherLoan = false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        sql.openConnection();
        scene = new Scene(new StackPane());
        WelcomeManager welcomeManager = new WelcomeManager(scene);
        welcomeManager.initializeScreen();

        stage.setTitle("Click2Loan");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(String.format("file:%s\\src\\main\\resources\\com\\example\\demo\\img\\app_icon.jpg", System.getProperty("user.dir"))));
        stage.show();
//        sql.closeConnection();

    }

    public static void markRedBox(Object obj, boolean unmark) {
        if (obj instanceof TextField)
            if (unmark)
                ((TextField) obj).setEffect(null);
            else
                ((TextField) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));

        if (obj instanceof PasswordField)
            if (unmark)
                ((PasswordField) obj).setEffect(null);
            else
                ((PasswordField) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));

        if (obj instanceof CheckBox)
            if (unmark)
                ((CheckBox) obj).setEffect(null);
            else
                ((CheckBox) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));

        if (obj instanceof ComboBox)
            if (unmark)
                ((ComboBox<?>) obj).setEffect(null);
            else
                ((ComboBox<?>) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));

        if (obj instanceof ChoiceBox)
            if (unmark)
                ((ChoiceBox<?>) obj).setEffect(null);
            else
                ((ChoiceBox<?>) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));

        if (obj instanceof DatePicker)
            if (unmark)
                ((DatePicker) obj).setEffect(null);
            else
                ((DatePicker) obj).setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(255, 0, 0), 7.0, 0.03, 0, 0));
    }

    public Scene getScene() {
        return scene;
    }

}
