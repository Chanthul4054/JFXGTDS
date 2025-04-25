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
        salePriceField.setText(Validation.formatDouble(tx.getSalePrice().get()));
        internalPriceField.setText(Validation.formatDouble(tx.getInternalPrice().get()));
        discountField.setText(Validation.formatDouble(tx.getDiscount().get()));
        quantityField.setText(Validation.formatDouble(tx.getQuantity().get()));

    }

    @FXML
    public void onClickSave(ActionEvent actionEvent) {
        transaction.setBillNumber(billNumberField.getText());
        transaction.setItemCode(itemCodeField.getText());
        transaction.setInternalPrice(Double.parseDouble(internalPriceField.getText()));
        transaction.setDiscount(Double.parseDouble(discountField.getText()));
        transaction.setSalePrice(Double.parseDouble(salePriceField.getText()));
        transaction.setQuantity(Double.parseDouble(quantityField.getText()));

        String lineChecksum =billNumberField.getText()+","+itemCodeField.getText()+","+internalPriceField.getText()+","+discountField.getText()+","+salePriceField.getText()+","+quantityField.getText();
        int newChecksum = Validation.checksum(lineChecksum);
        transaction.getChecksum().set(newChecksum);

        transaction.setValid(Validation.validCheck(transaction));

        Stage stage = (Stage) billNumberField.getScene().getWindow();
        stage.close();
    }



}
