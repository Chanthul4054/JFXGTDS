package com.iit.tutorials.jfxgtds;

import com.iit.tutorials.jfxgtds.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

public class CSVImport {
    public static ObservableList<Transaction> importCSV(File csvFile) {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;

            while((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 7){
                    String billNumber = parts[0].trim();
                    String itemCode = parts[1].trim();
                    double internalPrice = Double.parseDouble(parts[2].trim());
                    double quantity = Double.parseDouble(parts[3].trim());
                    double discount = Double.parseDouble(parts[4].trim());
                    double salePrice = Double.parseDouble(parts[5].trim());
                    int checksum = Integer.parseInt(parts[6].trim());

                    transactions.add(new Transaction(billNumber, itemCode, internalPrice, quantity, discount, salePrice, checksum, 0));

                }
            }
        }catch (IOException | NumberFormatException e) {
            System.out.println("Error reading CSV file" + e.getMessage());
        }
        return transactions;
    }
}
