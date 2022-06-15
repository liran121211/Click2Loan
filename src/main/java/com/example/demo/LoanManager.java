package com.example.demo;

import core.ann.classifier.Matrix;
import core.ann.classifier.MatrixExceptionHandler;
import core.ann.classifier.NeuralNetwork;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class LoanManager implements PropertyChangeListener {
    private final static int PAGE_1 = 0;
    private final static int PAGE_2 = 1;
    private final static int PAGE_3 = 2;
    private final static int PAGE_4 = 3;
    private final static int PAGE_5 = 4;
    private final static int FINAL_PAGE = 5;
    private final static int APPROVAL_PAGE = 6;
    private final static int REJECTION_PAGE = 7;
    private final static String UNDEFINED = "";
    private final static int WINDOW_WIDTH = 495;
    private final static int WINDOW_HEIGHT = 675;
    private static int TIMELINE_OFFSET = 0;
    public static Boolean is_file_downloaded = false;

    private final static double EPSILON = 0.83;

    private final Scene scene;
    private FXMLLoader loader;
    private final ThreadWorker_1 ann_loader;
    private final ThreadWorker_2 form_data_normalizer;
    private final ThreadWorker_3 ann_downloader;
    private final Preferences loan_form = Preferences.userRoot().node("LOAN FORM");


    public LoanManager(Scene scene) {
        this.scene = scene;
        this.ann_loader = new ThreadWorker_1();
        this.form_data_normalizer = new ThreadWorker_2();
        this.ann_downloader = new ThreadWorker_3();
        this.ann_loader.getNotifier().addPropertyChangeListener(this); // set this class as observer to ThreadWorker_1
        this.form_data_normalizer.getNotifier().addPropertyChangeListener(this); // set this class as observer to ThreadWorker_2
        this.ann_downloader.getNotifier().addPropertyChangeListener(this); // set this class as observer to ThreadWorker_3

        //loan_form.clear();// clear HDD saved data
    }

    /**
     * show loan screen
     */
    public void initializeScreen() {
        try {
            LoanController.current_page = 0;
            this.loader = new FXMLLoader(getClass().getResource("loan1.fxml"));
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            LoanController controller = loader.getController();
            controller.initManager(this);
            reloadForm(); // reload previous saved data
            ann_downloader.startTheService(); // start downloading ann_core_file
        } catch (IOException e) {
            Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * go back from loan page to welcome page
     */
    public void returnWelcomeScreen() {
        WelcomeManager welcomeManager = new WelcomeManager(scene);
        welcomeManager.initializeScreen();
    }

    public void continueRegistrationScreen() {
        RegisterManager registerManager = new RegisterManager(scene);
        registerManager.initializeScreen(loan_form);
    }

    public void nextPage(int currentPage) {
        if (currentPage == PAGE_1) {
            try {
                this.loader = new FXMLLoader(getClass().getResource("loan1.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager(this);
                reloadForm();
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (currentPage == PAGE_2) {
            try {
                savePage1(); // save current data
                this.loader = new FXMLLoader(getClass().getResource("loan2.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager2(this);
                reloadForm();
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (currentPage == PAGE_3) {
            try {
                savePage2(); // save current data
                this.loader = new FXMLLoader(getClass().getResource("loan3.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager3(this);
                reloadForm();
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (currentPage == PAGE_4) {
            try {
                savePage3(); // save current data
                this.loader = new FXMLLoader(getClass().getResource("loan4.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager4(this);
                reloadForm();
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (currentPage == PAGE_5) {
            try {
                savePage4(); // save current data
                this.loader = new FXMLLoader(getClass().getResource("loan5.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager5(this);
                controller.Agreement().getEngine().loadContent(FormAdapter.LOAN_AGREEMENT);
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (currentPage == FINAL_PAGE) { // loan calculation page
            try {
                savePage5(); // save current data
                this.loader = new FXMLLoader(getClass().getResource("submitLoan.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                ann_loader.startTheService();
                updateStatus("Building the magical network...");
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (currentPage == APPROVAL_PAGE) { // submit for now
            try {
                this.loader = new FXMLLoader(getClass().getResource("loanApproved.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager6(this);
                ((LoanController) loader.getController()).getFull_name_label().setText(loan_form.get("full_name", "UNKNOWN"));
                ((LoanController) loader.getController()).getAddress_name_label().setText(loan_form.get("address", "UNKNOWN"));
                ((LoanController) loader.getController()).getCounty_label().setText(loan_form.get("state", "UNKNOWN") + ", " + loan_form.getInt("zipcode", 0));
                ((LoanController) loader.getController()).getCountry_label().setText(loan_form.get("country", "UNKNOWN"));
                ((LoanController) loader.getController()).getOriginal_loan_label().setText("Total Requested Loan: $" + loan_form.getDouble("loan_amount", 0.0));
                ((LoanController) loader.getController()).getApproved_loan_label().setText("Total Approved Loan: $" + loan_form.getDouble("loan_amount", 0.0));

            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if (currentPage == REJECTION_PAGE) { // submit for now
            try {
                this.loader = new FXMLLoader(getClass().getResource("loanRejected.fxml"));
                scene.setRoot(loader.load());
                scene.getWindow().setWidth(WINDOW_WIDTH);
                scene.getWindow().setHeight(WINDOW_HEIGHT);
                scene.setUserData(loader);

                LoanController controller = loader.getController();
                controller.initManager7(this);
                ((LoanController) loader.getController()).getFull_name_label().setText(loan_form.get("full_name", "UNKNOWN"));
                ((LoanController) loader.getController()).getAddress_name_label().setText(loan_form.get("address", "UNKNOWN"));
                ((LoanController) loader.getController()).getCounty_label().setText(loan_form.get("state", "UNKNOWN") + ", " + loan_form.getInt("zipcode", 0));
                ((LoanController) loader.getController()).getCountry_label().setText(loan_form.get("country", "UNKNOWN"));
                ((LoanController) loader.getController()).getOriginal_loan_label().setText("Total Requested Loan: $" + loan_form.getDouble("loan_amount", 0.0));
                ((LoanController) loader.getController()).getApproved_loan_label().setText("Total Approved Loan: $" + loan_form.getDouble("loan_amount", 0.0));
            } catch (IOException e) {
                Logger.getLogger(WelcomeManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    private void savePage1() {
        LoanController controller = loader.getController(); // make code readable

        if (controller.fullName() != null && controller.fullName().getText().length() > 0)
            loan_form.put("full_name", controller.fullName().getText());

        if (controller.Address() != null && controller.Address().getText().length() > 0)
            loan_form.put("address", controller.Address().getText());

        if (controller.City() != null && controller.City().getText().length() > 0)
            loan_form.put("city", controller.City().getText());

        if (controller.State() != null && controller.State().getValue().length() > 0)
            loan_form.put("state", controller.State().getValue());

        if (controller.Country() != null && controller.Country().getValue().length() > 0)
            loan_form.put("country", controller.Country().getValue());

        if (controller.Zipcode() != null && controller.Zipcode().getText().length() > 0)
            loan_form.putInt("zipcode", Integer.parseInt(controller.Zipcode().getText()));

        if (controller.dateOfBirth() != null && LoanController.dateToDays(controller.dateOfBirth()) != 0)
            loan_form.putInt("dob_days", (int) LoanController.dateToDays(controller.dateOfBirth()));

        if (controller.Gender() != null && controller.Gender().getValue().length() > 0)
            loan_form.put("gender", controller.Gender().getValue());

    }

    private void savePage2() {
        LoanController controller = loader.getController(); // make code readable
        if (controller.totalIncome() != null && controller.totalIncome().getText().length() > 0)
            loan_form.putDouble("total_income", Double.parseDouble(controller.totalIncome().getText()));

        if (controller.propertyValue() != null && controller.ownRealtyFlag().isSelected())
            loan_form.putDouble("property_value", Double.parseDouble(controller.propertyValue().getText()));
        else
            loan_form.putInt("property_value", 0);

        if (controller.loanAmount() != null && controller.loanAmount().getText().length() > 0)
            loan_form.putDouble("loan_amount", Double.parseDouble(controller.loanAmount().getText()));

        if (controller.LivingType() != null && controller.LivingType().getValue().length() > 0)
            loan_form.put("living_type", controller.LivingType().getValue());

        if (controller.occupationType() != null && controller.occupationType().getValue().length() > 0)
            loan_form.put("occupation_type", controller.occupationType().getValue());

        if (controller.organizationType() != null && controller.organizationType().getValue().length() > 0)
            loan_form.put("organization_type", controller.organizationType().getValue());

        if (controller.ownCarAge() != null && controller.ownCarFlag().isSelected())
            loan_form.putInt("own_car_age", Integer.parseInt(controller.ownCarAge().getText()));
        else
            loan_form.putInt("own_car_age", 0);

        if (controller.daysEmployed() != null && LoanController.dateToDays(controller.daysEmployed()) != 0)
            loan_form.putInt("days_employed", (int) LoanController.dateToDays(controller.daysEmployed()));

        loan_form.putBoolean("own_car_flag", controller.ownCarFlag() != null && controller.ownCarFlag().isSelected());

        loan_form.putBoolean("own_realty_flag", controller.ownRealtyFlag() != null && controller.ownRealtyFlag().isSelected());

    }

    public void savePage3() {
        LoanController controller = loader.getController(); // make code readable

        if (controller.familyStatus() != null && controller.familyStatus().getValue().length() > 0)
            loan_form.put("family_status", controller.familyStatus().getValue());

        if (controller.childrensAmount() != null && controller.childrensAmount().getText().length() > 0)
            loan_form.putInt("children_amount", Integer.parseInt(controller.childrensAmount().getText()));

        if (controller.familyMembers() != null && controller.familyMembers().getText().length() > 0)
            loan_form.putInt("family_members", Integer.parseInt(controller.familyMembers().getText()));

        if (controller.educationLevel() != null && controller.educationLevel().getValue().length() > 0)
            loan_form.put("education_level", controller.educationLevel().getValue());

        if (controller.homePhone() != null && controller.homePhone().getText().length() > 0)
            loan_form.put("home_phone", controller.homePhone().getText());

        if (controller.mobilePhone() != null && controller.mobilePhone().getText().length() > 0)
            loan_form.put("mobile_phone", controller.mobilePhone().getText());

        if (controller.workPhone() != null && controller.workPhone().getText().length() > 0)
            loan_form.put("work_phone", controller.workPhone().getText());

        if (controller.Email() != null && controller.Email().getText().length() > 0)
            loan_form.put("email", controller.Email().getText());

        loan_form.putBoolean("home_phone_flag", controller.homePhoneNA() == null || !controller.homePhoneNA().isSelected());

        loan_form.putBoolean("mobile_phone_flag", controller.mobilePhoneNA() == null || !controller.mobilePhoneNA().isSelected());

        loan_form.putBoolean("work_phone_flag", controller.workPhoneNA() == null || !controller.workPhoneNA().isSelected());

        loan_form.putBoolean("email_flag", controller.EmailNA() == null || !controller.EmailNA().isSelected());
    }

    public void savePage4() {
        LoanController controller = loader.getController(); // make code readable

        loan_form.putBoolean("doc_2_flag", controller.docButton0() != null && controller.docButton0().isSelected());
        loan_form.putBoolean("doc_3_flag", controller.docButton1() != null && controller.docButton1().isSelected());
        loan_form.putBoolean("doc_4_flag", controller.docButton2() != null && controller.docButton2().isSelected());
        loan_form.putBoolean("doc_5_flag", controller.docButton3() != null && controller.docButton3().isSelected());
        loan_form.putBoolean("doc_6_flag", controller.docButton4() != null && controller.docButton4().isSelected());
        loan_form.putBoolean("doc_7_flag", controller.docButton5() != null && controller.docButton5().isSelected());
        loan_form.putBoolean("doc_8_flag", controller.docButton6() != null && controller.docButton6().isSelected());
        loan_form.putBoolean("doc_9_flag", controller.docButton7() != null && controller.docButton7().isSelected());
        loan_form.putBoolean("doc_10_flag", controller.docButton8() != null && controller.docButton8().isSelected());
        loan_form.putBoolean("doc_11_flag", controller.docButton9() != null && controller.docButton9().isSelected());
        loan_form.putBoolean("doc_12_flag", controller.docButton10() != null && controller.docButton10().isSelected());
        loan_form.putBoolean("doc_13_flag", controller.docButton11() != null && controller.docButton11().isSelected());
        loan_form.putBoolean("doc_14_flag", controller.docButton12() != null && controller.docButton12().isSelected());
        loan_form.putBoolean("doc_15_flag", controller.docButton13() != null && controller.docButton13().isSelected());
        loan_form.putBoolean("doc_16_flag", controller.docButton14() != null && controller.docButton14().isSelected());
        loan_form.putBoolean("doc_17_flag", controller.docButton15() != null && controller.docButton15().isSelected());
        loan_form.putBoolean("doc_18_flag", controller.docButton16() != null && controller.docButton16().isSelected());
        loan_form.putBoolean("doc_19_flag", controller.docButton17() != null && controller.docButton17().isSelected());
        loan_form.putBoolean("doc_20_flag", controller.docButton18() != null && controller.docButton18().isSelected());
        loan_form.putBoolean("doc_21_flag", controller.docButton19() != null && controller.docButton19().isSelected());
    }

    public void savePage5() {
        loan_form.put("agreement", "accepted");
    }


    private void reloadForm() {
        LoanController controller = loader.getController(); // make code readable

        //////////
        // PAGE 1
        //////////
        if (loan_form.get("full_name", UNDEFINED).compareTo("") != 0 && controller.fullName() != null)
            controller.fullName().setText(loan_form.get("full_name", UNDEFINED));

        if (loan_form.get("address", UNDEFINED).compareTo("") != 0 && controller.Address() != null)
            controller.Address().setText(loan_form.get("address", UNDEFINED));

        if (loan_form.get("city", UNDEFINED).compareTo("") != 0 && controller.City() != null)
            controller.City().setText(loan_form.get("city", UNDEFINED));

        if (loan_form.get("state", UNDEFINED).compareTo("") != 0 && controller.State() != null)
            controller.State().getSelectionModel().select(loan_form.get("state", UNDEFINED));

        if (loan_form.get("country", UNDEFINED).compareTo("") != 0 && controller.Country() != null)
            controller.Country().getSelectionModel().select(loan_form.get("country", UNDEFINED));

        if (loan_form.get("zipcode", UNDEFINED).compareTo("") != 0 && controller.Zipcode() != null)
            controller.Zipcode().setText(loan_form.get("zipcode", UNDEFINED));

        if (loan_form.get("dob_days", UNDEFINED).compareTo("") != 0 && controller.dateOfBirth() != null)
            controller.dateOfBirth().setValue(LoanController.daysToDate(loan_form.getInt("dob_days", 0)));


        if (loan_form.get("gender", UNDEFINED).compareTo("") != 0 && controller.Gender() != null)
            controller.Gender().getSelectionModel().select(loan_form.get("gender", UNDEFINED));

        //////////
        // PAGE 2
        //////////
        if (loan_form.get("total_income", UNDEFINED).compareTo("") != 0 && controller.totalIncome() != null)
            controller.totalIncome().setText(loan_form.get("total_income", UNDEFINED));

        if (loan_form.get("property_value", UNDEFINED).compareTo("") != 0 && controller.propertyValue() != null)
            controller.propertyValue().setText(loan_form.get("property_value", UNDEFINED));

        if (loan_form.get("loan_amount", UNDEFINED).compareTo("") != 0 && controller.loanAmount() != null)
            controller.loanAmount().setText(loan_form.get("loan_amount", UNDEFINED));

        if (loan_form.get("living_type", UNDEFINED).compareTo("") != 0 && controller.LivingType() != null)
            controller.LivingType().getSelectionModel().select(loan_form.get("living_type", UNDEFINED));

        if (loan_form.get("occupation_type", UNDEFINED).compareTo("") != 0 && controller.occupationType() != null)
            controller.occupationType().getSelectionModel().select(loan_form.get("occupation_type", UNDEFINED));

        if (loan_form.get("organization_type", UNDEFINED).compareTo("") != 0 && controller.organizationType() != null)
            controller.organizationType().getSelectionModel().select(loan_form.get("organization_type", UNDEFINED));

        if (loan_form.get("own_car_age", UNDEFINED).compareTo("") != 0 && controller.ownCarAge() != null)
            controller.ownCarAge().setText(loan_form.get("own_car_age", UNDEFINED));

        if (loan_form.get("days_employed", UNDEFINED).compareTo("") != 0 && controller.daysEmployed() != null)
            controller.daysEmployed().setValue(LoanController.daysToDate(loan_form.getInt("days_employed", 0)));

        if (loan_form.getBoolean("own_car_flag", false) && controller.ownCarFlag() != null) {
            controller.ownCarFlag().setSelected(true);
            controller.ownCarAge().setDisable(false);
        }

        if (loan_form.getBoolean("own_realty_flag", false) && controller.ownRealtyFlag() != null) {
            controller.ownRealtyFlag().setSelected(true);
            controller.propertyValue().setDisable(false);
        }


        //////////
        // PAGE 3
        //////////
        if (loan_form.get("family_status", UNDEFINED).compareTo("") != 0 && controller.familyStatus() != null)
            controller.familyStatus().getSelectionModel().select(loan_form.get("family_status", UNDEFINED));

        if (loan_form.get("children_amount", UNDEFINED).compareTo("") != 0 && controller.childrensAmount() != null)
            controller.childrensAmount().setText(loan_form.get("children_amount", UNDEFINED));

        if (loan_form.get("family_members", UNDEFINED).compareTo("") != 0 && controller.familyMembers() != null)
            controller.familyMembers().setText(loan_form.get("family_members", UNDEFINED));

        if (loan_form.get("education_level", UNDEFINED).compareTo("") != 0 && controller.educationLevel() != null)
            controller.educationLevel().getSelectionModel().select(loan_form.get("education_level", UNDEFINED));

        if (loan_form.get("home_phone", UNDEFINED).compareTo("") != 0 && controller.homePhone() != null)
            controller.homePhone().setText(loan_form.get("home_phone", UNDEFINED));

        if (loan_form.get("mobile_phone", UNDEFINED).compareTo("") != 0 && controller.mobilePhone() != null)
            controller.mobilePhone().setText(loan_form.get("mobile_phone", UNDEFINED));

        if (loan_form.get("work_phone", UNDEFINED).compareTo("") != 0 && controller.workPhone() != null)
            controller.workPhone().setText(loan_form.get("work_phone", UNDEFINED));

        if (loan_form.get("email", UNDEFINED).compareTo("") != 0 && controller.Email() != null)
            controller.Email().setText(loan_form.get("email", UNDEFINED));

        if (!loan_form.getBoolean("home_phone_flag", false) && controller.homePhoneNA() != null) {
            controller.homePhoneNA().setSelected(true);
            controller.homePhone().setDisable(true);
        }

        if (!loan_form.getBoolean("mobile_phone_flag", false) && controller.mobilePhoneNA() != null) {
            controller.mobilePhoneNA().setSelected(true);
            controller.mobilePhone().setDisable(true);
        }

        if (!loan_form.getBoolean("work_phone_flag", false) && controller.workPhoneNA() != null) {
            controller.workPhoneNA().setSelected(true);
            controller.workPhone().setDisable(true);
        }

        if (!loan_form.getBoolean("email_flag", false) && controller.EmailNA() != null) {
            controller.EmailNA().setSelected(true);
            controller.Email().setDisable(true);
        }

        //////////
        // PAGE 4
        //////////
        if (loan_form.getBoolean("doc_2_flag", false) && controller.docButton0() != null)
            controller.docButton0().setSelected(true);

        if (loan_form.getBoolean("doc_3_flag", false) && controller.docButton1() != null)
            controller.docButton1().setSelected(true);

        if (loan_form.getBoolean("doc_4_flag", false) && controller.docButton2() != null)
            controller.docButton2().setSelected(true);

        if (loan_form.getBoolean("doc_5_flag", false) && controller.docButton3() != null)
            controller.docButton3().setSelected(true);

        if (loan_form.getBoolean("doc_6_flag", false) && controller.docButton4() != null)
            controller.docButton4().setSelected(true);

        if (loan_form.getBoolean("doc_7_flag", false) && controller.docButton5() != null)
            controller.docButton5().setSelected(true);

        if (loan_form.getBoolean("doc_8_flag", false) && controller.docButton6() != null)
            controller.docButton6().setSelected(true);

        if (loan_form.getBoolean("doc_9_flag", false) && controller.docButton7() != null)
            controller.docButton7().setSelected(true);

        if (loan_form.getBoolean("doc_10_flag", false) && controller.docButton8() != null)
            controller.docButton8().setSelected(true);

        if (loan_form.getBoolean("doc_11_flag", false) && controller.docButton9() != null)
            controller.docButton9().setSelected(true);

        if (loan_form.getBoolean("doc_12_flag", false) && controller.docButton10() != null)
            controller.docButton10().setSelected(true);

        if (loan_form.getBoolean("doc_13_flag", false) && controller.docButton11() != null)
            controller.docButton11().setSelected(true);

        if (loan_form.getBoolean("doc_14_flag", false) && controller.docButton12() != null)
            controller.docButton12().setSelected(true);

        if (loan_form.getBoolean("doc_15_flag", false) && controller.docButton13() != null)
            controller.docButton13().setSelected(true);

        if (loan_form.getBoolean("doc_16_flag", false) && controller.docButton14() != null)
            controller.docButton14().setSelected(true);

        if (loan_form.getBoolean("doc_17_flag", false) && controller.docButton15() != null)
            controller.docButton15().setSelected(true);

        if (loan_form.getBoolean("doc_18_flag", false) && controller.docButton16() != null)
            controller.docButton16().setSelected(true);

        if (loan_form.getBoolean("doc_19_flag", false) && controller.docButton17() != null)
            controller.docButton17().setSelected(true);

        if (loan_form.getBoolean("doc_20_flag", false) && controller.docButton18() != null)
            controller.docButton18().setSelected(true);

        if (loan_form.getBoolean("doc_21_flag", false) && controller.docButton19() != null)
            controller.docButton19().setSelected(true);

    }

    private void loanResult(Matrix A) {
        NeuralNetwork local_ann = ann_loader.getValue(); // get loaded data of NeuralNetwork file.
        try {
            local_ann.predict(A); // convert (loan_form) to ANN form

            if (local_ann.get_predictions().argmax(0).getValue(0, 0) > EPSILON) { // if loan approved
                //System.out.println(local_ann.get_predictions().argmax(0));
                nextPage(++LoanController.current_page);
                loan_form.put("loan_status", "approved");
                loan_form.put("reference_id", getReferenceID(8));
            } else { // if loan denied
                ++LoanController.current_page;
                //System.out.println(local_ann.get_predictions().argmax(0));
                nextPage(++LoanController.current_page);
                loan_form.put("loan_status", "rejected");
                loan_form.put("reference_id", getReferenceID(8));
            }

        } catch (MatrixExceptionHandler e) {
            e.printStackTrace();
        }
    }

    private void formDataToCSV() throws MatrixExceptionHandler, IOException {
        FormAdapter form_data = new FormAdapter(loan_form); // load Preferences load data
        Matrix temp = form_data.preferencesConverter(); // convert Preferences to Matrix

        FileWriter encoded_writer = new FileWriter("src\\main\\java\\core\\bin\\metrics\\loan_encoded.csv"); //write to file
        List<String> loan_params = new ArrayList<>(43); // convert data into list
        for (int i = 0; i < temp.getColumns(); i++)
            loan_params.add(String.valueOf(temp.getValue(0, i)));

        String loan_encoded = String.join(",", loan_params); // convert data to csv type
        encoded_writer.write(loan_encoded); // save as csv file
        encoded_writer.close(); // close buffer
    }

    private Matrix normalizeCSV() throws MatrixExceptionHandler, IOException {
        Matrix result = new Matrix(1, 43);
        BufferedReader buffer_reader;
        String current_line;
        int row_cnt = 0;
        buffer_reader = new BufferedReader(new FileReader("src\\main\\java\\core\\bin\\metrics\\loan_normalized.csv"));

        while ((current_line = buffer_reader.readLine()) != null) {
            String[] row = current_line.split(",");    // use comma as separator
            for (int i = 0; i < result.getColumns(); i++) {
                double val = Double.parseDouble(row[i]);
                result.setValue(row_cnt, i, val);
            }
            row_cnt++;
        }
        return result;
    }

    private void updateStatus(String text) { // loading text when calculating loan status
        Label temp = ((LoanController) loader.getController()).loadingStatus();
        TIMELINE_OFFSET += 2500;
        new Timeline(new KeyFrame(Duration.millis(TIMELINE_OFFSET), ignore -> temp.setText(text))).play();

    }

    static String getReferenceID(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuilder r = new StringBuilder();

        // remove all spacial char
        String AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().compareTo("LOADER_STATUS") == 0 && evt.getNewValue().toString().compareTo("SUCCESS") == 0) {
            updateStatus("Let's see the information you've sent us...");
            updateStatus("Setting up one last thing...");
            updateStatus("We've come into conclusion...");
            new Timeline(new KeyFrame(Duration.millis(TIMELINE_OFFSET), ignore -> {
                try {
                    formDataToCSV();
                    form_data_normalizer.startTheService();
                } catch (MatrixExceptionHandler | IOException e) {
                    e.printStackTrace();
                }
            })).play();
        }


        if (evt.getPropertyName().compareTo("PYTHON_STATUS") == 0 && ((String) (evt.getNewValue())).compareTo("SUCCESS") == 0) {
            try {
                loanResult(normalizeCSV());
            } catch (MatrixExceptionHandler | IOException e) {
                e.printStackTrace();
            }
        }
        if (evt.getPropertyName().compareTo("DOWNLOAD_STATUS") == 0 && ((String) (evt.getNewValue())).compareTo("SUCCESS") == 0) {
            LoanManager.is_file_downloaded = true;
        }
    }

    public Scene getScene() {
        return scene;
    }
}


