package com.iit.tutorials.jfxgtds;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLOutput;

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
    }

    public void onClickValidate(ActionEvent actionEvent) {
        int total = transactionList.size();
        int valid = 0;
        int invalid = 0;

        for (Transaction tx : transactionList) {
            if (tx.isValid()) {
                valid++;
            } else {
                invalid++;
            }
        }
        totalRecordsLabel.setText(Integer.toString(total));
        validLabels.setText(Integer.toString(valid));
        invalidLabels.setText(Integer.toString(invalid));
    }
}