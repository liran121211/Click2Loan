package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class RegisterManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 495;
    private final static int WINDOW_HEIGHT = 675;
    private final static String STRING_KEY_ERROR = "-1";
    private final static int INT_KEY_ERROR = -1;
    private final static double DOUBLE_KEY_ERROR = -1.0;

    private Preferences loan_form;


    public RegisterManager(Scene scene) {
        this.scene = scene;
    }

    public void initializeScreen(Preferences loan_form) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("register.fxml")
            );
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);
            this.loan_form = loan_form;

            RegisterController controller = loader.getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goWelcome() {
        WelcomeManager welcomeManager = new WelcomeManager(scene);
        welcomeManager.initializeScreen();
    }


    protected void createAccount(PasswordField password, PasswordField repeat_password, TextField email, TextField username, CheckBox agreement) throws SQLException {
        boolean isFormInvalid = false;

        // Form Validation
        if (password.getText().length() == 0) {
            isFormInvalid = true;
            LoanApp.markRedBox(password, false);
        } else
            LoanApp.markRedBox(password, true);

        if (repeat_password.getText().length() == 0 && repeat_password.getText().compareTo(password.getText()) != 0) {
            isFormInvalid = true;
            LoanApp.markRedBox(repeat_password, false);
        } else
            LoanApp.markRedBox(repeat_password, true);

        if (email.getText().length() == 0) {
            isFormInvalid = true;
            LoanApp.markRedBox(email, false);
        } else
            LoanApp.markRedBox(email, true);

        if (username.getText().length() == 0) {
            isFormInvalid = true;
            LoanApp.markRedBox(username, false);
        } else
            LoanApp.markRedBox(username, true);

        if (!agreement.isSelected()) {
            isFormInvalid = true;
            LoanApp.markRedBox(agreement, false);
        } else
            LoanApp.markRedBox(agreement, true);


        // Check Form Validation
        if (!isFormInvalid) {

            // User Creation
            String u_registration_cols = "username, email, password, role";
            LoanApp.sql.insert("users", String.format("%s", u_registration_cols), String.format("'%s','%s','%s',0", username.getText(), email.getText(), password.getText()));

            //Loan Form Creation
            String user_id = LoanApp.sql.select("users", "id", String.format("username='%s'", username.getText()))[0][0];
            LoanApp.sql.insert("loan_form_data",
                    "address," +
                            " amt_credit," +
                            " amt_goods_price, " +
                            "amt_income_total, " +
                            "city, " +
                            "cnt_children, " +
                            "cnt_fam_members, " +
                            "code_gender, " +
                            "country, " +
                            "county, " +
                            "days_employed, " +
                            "first_name, " +
                            "flag_email, " +
                            "flag_emp_phone, " +
                            "flag_mobil, " +
                            "flag_own_car, " +
                            "flag_own_realty, " +
                            "flag_phone, " +
                            "flag_work_phone, " +
                            "last_name, " +
                            "name_education_type, " +
                            "name_family_status, " +
                            "name_housing_type, " +
                            "occupation_type, " +
                            "organization_type, " +
                            "own_car_age, " +
                            "phone, " +
                            "region_population_relative," +
                            "region_rating_client, " +
                            "initial_status, " +
                            "user_id, " +
                            "zipcode, " +
                            "reference_id",
                    String.format("'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%s,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%s,'%s','%s','%s','%s',%s,'%s','%s'",
                            loan_form.get("address", STRING_KEY_ERROR),
                            loan_form.getDouble("loan_amount", INT_KEY_ERROR),
                            loan_form.getDouble("property_value", INT_KEY_ERROR),
                            loan_form.getDouble("total_income", INT_KEY_ERROR),
                            loan_form.get("city", STRING_KEY_ERROR),
                            loan_form.getInt("children_amount", INT_KEY_ERROR),
                            loan_form.getInt("family_members", INT_KEY_ERROR),
                            loan_form.get("gender", STRING_KEY_ERROR),
                            loan_form.get("country", STRING_KEY_ERROR),
                            loan_form.get("state", STRING_KEY_ERROR),
                            loan_form.getInt("days_employed", INT_KEY_ERROR),
                            loan_form.get("full_name", STRING_KEY_ERROR).split(" ", 2)[0],
                            loan_form.getBoolean("email_flag", false),
                            loan_form.getBoolean("work_phone_flag", false),
                            loan_form.getBoolean("mobile_phone_flag", false),
                            loan_form.getBoolean("own_car_flag", false),
                            loan_form.getBoolean("own_realty_flag", false),
                            loan_form.getBoolean("home_phone", false),
                            loan_form.getBoolean("work_phone_flag", false),
                            loan_form.get("full_name", STRING_KEY_ERROR).split(" ", 2)[1],
                            loan_form.get("education_level", STRING_KEY_ERROR),
                            loan_form.get("family_status", STRING_KEY_ERROR),
                            loan_form.get("living_type", STRING_KEY_ERROR),
                            loan_form.get("occupation_type", STRING_KEY_ERROR),
                            loan_form.get("organization_type", STRING_KEY_ERROR),
                            loan_form.getInt("own_car_age", INT_KEY_ERROR),
                            loan_form.get("home_phone", STRING_KEY_ERROR),
                            regionPopulationRelative(),
                            regionRatingClient(),
                            loan_form.get("loan_status", STRING_KEY_ERROR),
                            user_id,
                            loan_form.getInt("zipcode", INT_KEY_ERROR),
                            loan_form.get("reference_id", STRING_KEY_ERROR)
                    )
            );


            //Client Creation
            String c_registration_cols = "first_name, last_name, street, city, country, zipcode, phone, user_id, bank_number, credits";
            String first_name = loan_form.get("full_name", STRING_KEY_ERROR).split(" ", 2)[0];
            String last_name = loan_form.get("full_name", STRING_KEY_ERROR).split(" ", 2)[1];
            String street = loan_form.get("address", STRING_KEY_ERROR);
            String city = loan_form.get("city", STRING_KEY_ERROR);
            String country = loan_form.get("country", STRING_KEY_ERROR);
            String zipcode = String.valueOf(loan_form.getInt("zipcode", INT_KEY_ERROR));
            String phone = loan_form.get("home_phone", STRING_KEY_ERROR);
            String credits = "0";
            if (loan_form.get("loan_status", STRING_KEY_ERROR).compareTo("accepted") == 0)
                credits = String.valueOf(loan_form.getDouble("loan_amount", DOUBLE_KEY_ERROR));

            LoanApp.sql.insert("clients", String.format("%s", c_registration_cols), String.format("'%s', '%s', '%s', '%s', '%s', %s, '%s', %s, %s, %s", first_name, last_name, street, city, country, zipcode, phone, user_id, 0, credits));

            // Loan Creation
            LocalDate current_time = LocalDate.now();
            String l_registration_cols = "id, user_id, request_date, loan_amount, remaining_amount, status";
            Double request_amount = loan_form.getDouble("loan_amount", DOUBLE_KEY_ERROR);
            String loan_id = LoanApp.sql.select("loan_form_data", "id", String.format("reference_id='%s'", loan_form.get("reference_id", STRING_KEY_ERROR)))[0][0];


            if (loan_form.get("loan_status", STRING_KEY_ERROR).compareTo("approved") == 0) {
                LoanApp.sql.insert("loans", String.format("%s", l_registration_cols), String.format("%s, %s, '%s', %s, %s, '%s'", loan_id, user_id, current_time, request_amount, request_amount, "APPROVED"));
                LoanApp.sql.update("clients", "credits", String.valueOf(loan_form.getDouble("loan_amount", INT_KEY_ERROR)), String.format("user_id=%s", user_id));

            } else
                LoanApp.sql.insert("loans", String.format("%s", l_registration_cols), String.format("%s, %s, '%s', %s, %s, '%s'", loan_id, user_id, current_time, request_amount, request_amount, "PENDING"));

            // After successful data insertion go back to login
            LoginManager loginManager = new LoginManager(scene);
            loginManager.initializeScreen();

        }
    }


    private int regionRatingClient() {
        if (loan_form.get("state", null).compareTo("North") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        if (loan_form.get("state", null).compareTo("Center") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        if (loan_form.get("state", null).compareTo("South") == 0)
            return REGION_RATING_CLIENT.NORTH.value();

        return -1;
    }

    private double regionPopulationRelative() {
        if ((loan_form.get("state", null).compareTo("North") == 0))
            return 0.03;
        if ((loan_form.get("state", null).compareTo("Center") == 0))
            return 0.07;
        else
            return 0.03;
    }

    public Scene getScene() {
        return scene;
    }
}
