package com.iit.tutorials.jfxgtds;

import com.iit.tutorials.jfxgtds.Transaction;

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
            else if (Character.isDigit(c)) {
                digit++;
            }
        }
        return uppercase + lowercase + digit;
    }

    public static boolean checkSpecialChar(String itemCode) {
        return itemCode.matches("[A-Za-z0-9]+");
    }

    public static int validCheck(Transaction tx){
        String lineChecksum = tx.getBillNumber() + "," + tx.getItemCode() + "," + tx.getInternalPrice() + "," + tx.getQuantity() + "," + tx.getDiscount() + "," + tx.getSalePrice();
        return checksum(lineChecksum);
    }
}
