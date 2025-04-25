package com.iit.tutorials.jfxgtds;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {

    @FXML
    public TextField billNumberField;
    @FXML
    public TextField itemCodeField;
    @FXML
    public TextField discountField;
    @FXML
    public TextField internalPriceField;
    @FXML
    public TextField salePriceField;
    @FXML
    public TextField quantityField;
    @FXML
    public Button saveButton;

    private Transaction transaction;

    public void setTransaction(Transaction tx) {
        this.transaction = tx;
        billNumberField.setText(tx.getBillNumber().get());
        itemCodeField.setText(tx.getItemCode().get());
        salePriceField.setText(String.valueOf(tx.getSalePrice()));
        internalPriceField.setText(String.valueOf(tx.getInternalPrice().get()));
        discountField.setText(String.valueOf(tx.getDiscount().get()));
        quantityField.setText(String.valueOf(tx.getQuantity()));
    }

    @FXML
    public void onClickSave(ActionEvent actionEvent) {



        String lineChecksum =billNumberField.getText()+","+itemCodeField.getText()+","+internalPriceField.getText()+","+discountField.getText()+","+salePriceField.getText()+","+quantityField.getText();
        int newChecksum = Validation.checksum(lineChecksum);
        transaction.getChecksum().set(newChecksum);

        transaction.setValid(Validation.validCheck(transaction));

        Stage stage = (Stage) billNumberField.getScene().getWindow();
        stage.close();
    }



}
