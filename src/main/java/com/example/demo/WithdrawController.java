package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WithdrawController {


    @FXML
    private Button withdrawButton, cancelButton;
    @FXML
    private TextField acc_num, branch, bank_name, full_name, amount;


    public void initManager(WithdrawManager withdrawManager) {
        withdrawButton.setOnAction(event -> withdrawManager.withdrawRequest(acc_num, branch, bank_name, full_name, amount));
        cancelButton.setOnAction(event -> withdrawManager.goBack());

    }

    public Button getWithdrawButton() {
        return withdrawButton;
    }

    public TextField getAcc_num() {
        return acc_num;
    }

    public TextField getBranch() {
        return branch;
    }

    public TextField getBank_name() {
        return bank_name;
    }

    public TextField getFull_name() {
        return full_name;
    }

    public TextField getAmount() {
        return amount;
    }
}
