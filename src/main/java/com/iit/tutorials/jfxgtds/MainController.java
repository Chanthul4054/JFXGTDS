package com.iit.tutorials.jfxgtds;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainController {

    @FXML
    public TableView<Transaction> transactionTable;
    @FXML
    public Label filePathBox;
    @FXML
    public TableColumn<Transaction, String> billNumberCol;
    @FXML
    public TableColumn<Transaction, String>itemCodeCol;
    @FXML
    public TableColumn<Transaction, Number>internalPriceCol;
    @FXML
    public TableColumn<Transaction, Number>discountCol;
    @FXML
    public TableColumn<Transaction, Number>salePriceCol;
    @FXML
    public TableColumn<Transaction, Number>quantityCol;
    @FXML
    public TableColumn<Transaction, Number>checksumCol;
    @FXML
    public Button chooseFileButton;
    @FXML
    public Button importButton;
    @FXML
    public Button validateButton;
    @FXML
    public Label totalRecordsLabel;
    @FXML
    public Label validLabels;
    @FXML
    public Label invalidLabels;
    @FXML
    public Label massegeLabel;

    private File selectedFile;
    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();

    @FXML
    public void onClickChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Transaction CSV File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        Stage stage = (Stage) filePathBox.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filePathBox.setText(selectedFile.getAbsolutePath());
        } else {
            filePathBox.setText("No file selected");
        }
    }

    @FXML
    public void onClickImport(ActionEvent actionEvent) {
        if (selectedFile != null) {
            transactionList.clear();
            transactionList.addAll(CSVImport.importCSV(selectedFile));
            System.out.println("Imported" + transactionList.size() + " transactions");
        } else {
            System.out.println("No file selected");

        }
    }

    @FXML
    public void initialize() {
        billNumberCol.setCellValueFactory(cellData -> cellData.getValue().getBillNumber());
        itemCodeCol.setCellValueFactory(cellData -> cellData.getValue().getItemCode());
        internalPriceCol.setCellValueFactory(cellData -> cellData.getValue().getInternalPrice());
        discountCol.setCellValueFactory(cellData -> cellData.getValue().getDiscount());
        salePriceCol.setCellValueFactory(cellData -> cellData.getValue().getSalePrice());
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
        checksumCol.setCellValueFactory(cellData -> cellData.getValue().getChecksum());

        transactionTable.setItems(transactionList);

        transactionTable.setRowFactory(tv -> new TableRow<>(){
            @Override
            protected void updateItem(Transaction item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty)
                    setStyle("");
                else{
                    if (!item.isValid()){
                        setStyle("-fx-background-color: #ce8a8a;");
                    }else{
                        setStyle("");
                    }
                }
            }
        });
    }

    public void onClickValidate(ActionEvent actionEvent) {
        if (transactionList.isEmpty()) {
            massegeLabel.setText("No transactions to validate");
            return;
        }
        int total = transactionList.size();
        int validCount = 0;
        int invalidCount = 0;

        for (Transaction tx : transactionList) {
            boolean isValid = Validation.validCheck(tx) ;
            tx.setValid(isValid);
            if (isValid) {
                validCount++;
            } else {
                invalidCount++;
            }

            transactionTable.refresh();

            totalRecordsLabel.setText(Integer.toString(total));
            validLabels.setText(Integer.toString(validCount));
            invalidLabels.setText(Integer.toString(invalidCount));
        }
    }

    public void onClickEdit(ActionEvent actionEvent) throws IOException {
        Transaction selected = transactionTable.getSelectionModel().getSelectedItem();
        if (selected != null && !selected.isValid()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-view.fxml"));
                Stage newStage = new Stage();
                newStage.setTitle("Edit Transaction");
                newStage.setScene(new Scene(loader.load()));

                EditController controller = loader.getController();
                controller.setTransaction(selected);
                newStage.showAndWait();

                transactionTable.refresh();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Invalid transaction selected");
        }
    }

    public void onClickDelete(ActionEvent actionEvent) {
        Transaction selected = transactionTable.getSelectionModel().getSelectedItem();
        if (selected != null && !selected.isValid()) {
            transactionList.remove(selected);
        }else{
            System.out.println("Invalid transaction selected");
            massegeLabel.setText("Invalid transaction selected");
        }

        transactionTable.refresh();
    }

    public void onClickSaveChangers(ActionEvent actionEvent) {
        if(transactionList.isEmpty()){
            System.out.println("No transactions to save");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        Stage stage = (Stage) filePathBox.getScene().getWindow();
        File saveFile = fileChooser.showSaveDialog(stage);

        if(saveFile != null){
            saveToFile(saveFile);
        }
    }

    public void saveToFile(File file){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("billNumber,itemCode,internalPrice,discount,salePrice,quantity,checksum");
            bw.newLine();
            for(Transaction tx : transactionList){
                String line = tx.getBillNumber().get()+","+tx.getItemCode().get()+","+Validation.formatDouble(tx.getInternalPrice().get())+","+Validation.formatDouble(tx.getDiscount().get())+","+Validation.formatDouble(tx.getSalePrice().get())+","+Validation.formatDouble(tx.getQuantity().get())+","+tx.getChecksum().get();
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
            showAlert("failed to save the file: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}