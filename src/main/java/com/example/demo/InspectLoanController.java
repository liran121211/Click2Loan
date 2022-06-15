package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

import static com.example.demo.FormAdapter.isNumeric;

public class InspectLoanController {
    @FXML
    private Button returnButton;
    @FXML
    private Label income_label, loan_amount_label, job_field_label, vehicle_year_label, own_car_label, property_value_label, lives_in_label, job_title_label, employed_since_label, own_property_label, family_status_label, family_members_label, home_phone_label, work_phone_label, children_label, education_label, mobile_phone_label, email_label, gender_label, zipcode_label, state_label, address_label, age_label, country_label, city_label, name_label;
    private int loan_id;

    public void initManager(InspectLoanManager inspectLoanManager, int loan_id) throws SQLException {
        this.loan_id = loan_id;

        returnButton.setOnAction(event -> inspectLoanManager.goBack());

        fetchLoan(); // load form data
    }

    private void fetchLoan() {
        try {
            String[] loan_form = LoanApp.sql.select("loan_form_data", "*", String.format("id=%s", loan_id))[0];
            name_label.setText(loan_form[25] + " " + loan_form[26]);
            address_label.setText(loan_form[27]);
            city_label.setText(loan_form[28]);
            state_label.setText(loan_form[30]);
            country_label.setText(loan_form[31]);
            zipcode_label.setText(loan_form[29]);
            if (isNumeric(loan_form[34]))
                age_label.setText(String.valueOf(Integer.parseInt(loan_form[34]) / 365));
            else
                age_label.setText("N/A");

            if (loan_form[2].compareTo("0") == 0)
                gender_label.setText("Male");
            else
                gender_label.setText("Female");

            income_label.setText(loan_form[6]);

            if (loan_form[4].compareTo("0") == 0) {
                property_value_label.setText("N/A");
                own_property_label.setText("No");
            } else {
                property_value_label.setText(loan_form[8]);
                own_property_label.setText("Yes");
            }


            loan_amount_label.setText(loan_form[7]);

            for (NAME_HOUSING_TYPE value : NAME_HOUSING_TYPE.values())
                if (isNumeric(loan_form[11])) {
                    if (value.value() == Integer.parseInt(loan_form[11]))
                        lives_in_label.setText(value.name());
                } else
                    lives_in_label.setText(loan_form[11]);

            for (ORGANIZATION_TYPE value : ORGANIZATION_TYPE.values())
                if (isNumeric(loan_form[20])) {
                    if (value.value() == Integer.parseInt(loan_form[20]))
                        job_field_label.setText(value.name());
                } else
                    job_field_label.setText(loan_form[20]);


            for (OCCUPATION_TYPE value : OCCUPATION_TYPE.values())
                if (isNumeric(loan_form[23])) {
                    if (value.value() == Integer.parseInt(loan_form[23]))
                        job_title_label.setText(value.name());
                } else
                    job_title_label.setText(loan_form[23]);

            if (loan_form[3].compareTo("0") == 0) {
                own_car_label.setText("No");
                vehicle_year_label.setText("N/A");
            } else {
                own_car_label.setText("Yes");
                vehicle_year_label.setText(loan_form[14]);
            }

            if (loan_form[13].compareTo("0") == 0)
                employed_since_label.setText("Unemployed");
            else
                employed_since_label.setText(String.valueOf(2022 - Integer.parseInt(loan_form[13]) / 365));

            for (NAME_FAMILY_STATUS value : NAME_FAMILY_STATUS.values())
                if (isNumeric(loan_form[10])) {
                    if (value.value() == Integer.parseInt(loan_form[10]))
                        family_status_label.setText(value.name());
                } else
                    family_status_label.setText(loan_form[10]);

            children_label.setText(loan_form[5]);
            family_members_label.setText(loan_form[21]);

            for (NAME_EDUCATION_TYPE value : NAME_EDUCATION_TYPE.values())
                if (isNumeric(loan_form[9])) {
                    if (value.value() == Integer.parseInt(loan_form[9]))
                        education_label.setText(value.name());
                } else
                    education_label.setText(loan_form[9]);

            if (loan_form[15].compareTo("0") == 0)
                mobile_phone_label.setText("N/A");
            else
                mobile_phone_label.setText("Confidential");

            if (loan_form[17].compareTo("0") == 0)
                work_phone_label.setText("N/A");
            else
                work_phone_label.setText(loan_form[32]);

            if (loan_form[16].compareTo("0") == 0)
                home_phone_label.setText("N/A");
            else
                home_phone_label.setText("Confidential");

            if (loan_form[19].compareTo("0") == 0)
                email_label.setText("N/A");
            else
                email_label.setText("Confidential");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

