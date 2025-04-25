package com.iit.tutorials.jfxgtds;

import javafx.beans.property.*;

public class Transaction {
    private StringProperty billNumber;
    private StringProperty itemCode;
    private DoubleProperty internalPrice;
    private DoubleProperty quantity;
    private DoubleProperty discount;
    private DoubleProperty salePrice;
    private IntegerProperty checksum;
    private DoubleProperty profit;
    private BooleanProperty valid = new SimpleBooleanProperty(true);


    public Transaction(String billNumber, String itemCode, double internalPrice, double quantity, double discount, double salePrice, int checksum, double profit) {
        this.billNumber = new SimpleStringProperty(billNumber);
        this.itemCode = new SimpleStringProperty(itemCode);
        this.internalPrice = new SimpleDoubleProperty(internalPrice);
        this.quantity = new SimpleDoubleProperty(quantity);
        this.discount = new SimpleDoubleProperty(discount);
        this.salePrice = new SimpleDoubleProperty(salePrice);
        this.checksum = new SimpleIntegerProperty(checksum);
        this.profit = new SimpleDoubleProperty(0);
    }

        public StringProperty getBillNumber() {
            return billNumber;
        }
        public StringProperty getItemCode() {
            return itemCode;
        }
        public DoubleProperty getInternalPrice() {
            return internalPrice;
        }
        public DoubleProperty getQuantity() {
            return quantity;
        }
        public DoubleProperty getDiscount() {
            return discount;
        }
        public DoubleProperty getSalePrice() {
            return salePrice;
        }
        public IntegerProperty getChecksum() {
            return checksum;
        }
        public DoubleProperty getProfit() {
            return profit;
        }
        public BooleanProperty valid(){
            return valid;
        }
        public boolean isValid(){
            return valid.get();
        }
        public void setValid(boolean validCheck){
            this.valid.set(validCheck);
        }
        public void setBillNumber(String billNumber) {
            this.billNumber.set(billNumber);
        }
        public void setItemCode(String itemCode) {
            this.itemCode.set(itemCode);
        }
        public void setInternalPrice(double internalPrice) {
            this.internalPrice.set(internalPrice);
        }
        public void setQuantity(double quantity) {
            this.quantity.set(quantity);
        }
        public void setDiscount(double discount) {
            this.discount.set(discount);
        }
        public void setSalePrice(double salePrice) {
            this.salePrice.set(salePrice);
        }
        public void setChecksum(int checksum) {
            this.checksum.set(checksum);
        }

}
