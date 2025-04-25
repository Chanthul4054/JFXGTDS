package com.iit.tutorials.jfxgtds;

import com.iit.tutorials.jfxgtds.Transaction;

import java.text.DecimalFormat;

public class Validation {
    public static int checksum(String line) {
        int uppercase = 0;
        int lowercase = 0;
        int digit = 0;
        for (char c : line.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercase++;
            }
            else if (Character.isLowerCase(c)) {
                lowercase++;
            }
            else if (Character.isDigit(c) || c == '.') {
                digit++;
            }
        }
        return uppercase + lowercase + digit;
    }

    public static boolean checkSpecialChar(String itemCode) {
        return !itemCode.matches("[A-Za-z0-9]+");
    }

    public static boolean validCheck(Transaction tx){
        String lineChecksum =tx.getBillNumber().get()+","+tx.getItemCode().get()+","+tx.getInternalPrice().get()+","+tx.getDiscount().get()+","+tx.getSalePrice().get()+","+tx.getQuantity().get();
        int calculatedChecksum = checksum(lineChecksum);
        boolean checksumCorrect = tx.getChecksum().get() == calculatedChecksum;
        boolean hasNoSpecialChar = !checkSpecialChar(tx.getItemCode().get());
        boolean noNegativePrice = tx.getSalePrice().get() >= 0 && tx.getInternalPrice().get() >= 0;

        return  hasNoSpecialChar && noNegativePrice && checksumCorrect;
    }

    public static String formatDouble(double value){
        return new DecimalFormat("#.0").format(value);
    }
}
