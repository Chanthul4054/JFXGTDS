package com.iit.tutorials.jfxgtds;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TaxController {

    @FXML
    public TextField taxRateField;
    @FXML
    public Label profitLabel;
    @FXML
    public Label taxLabel;
    @FXML
    public Label messageBox;


    private ObservableList<Transaction> transactionList;


    public void setTransactionList(ObservableList<Transaction> transactionList){
        this.transactionList = transactionList ; 
    }
    public void onClickCalculateTax(ActionEvent actionEvent) {
        if (transactionList == null || transactionList.isEmpty()) {
            messageBox.setText("No transactions to calculate");
            return;
        }
        double taxRate = Double.parseDouble(taxRateField.getText());
        double totalProfit = 0;
        for (Transaction tx : transactionList) {
            double profit = calculateProfit(tx);
            tx.setProfit(profit);
            totalProfit += profit;
        }

        double finalTax = totalProfit * taxRate / 100;
        taxLabel.setText(Validation.formatDouble(finalTax));
        profitLabel.setText(Validation.formatDouble(totalProfit));
        messageBox.setText("Tax calculated");
    }

    private double calculateProfit(Transaction tx) {
        double saleTotalPrice = tx.getSalePrice().get() - tx.getQuantity().get() * (100 - tx.getDiscount().get()/100);
        double internalTotalPrice = tx.getInternalPrice().get() * tx.getQuantity().get();
        return internalTotalPrice - saleTotalPrice;
    }
}
