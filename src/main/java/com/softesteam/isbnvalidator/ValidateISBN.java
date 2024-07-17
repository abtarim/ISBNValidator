package com.softesteam.isbnvalidator;

public class ValidateISBN {
    public boolean checkISBN  (String numberOfISBN) {
        int total = 0;

        if (numberOfISBN.length() != 10) {
            System.out.println("ISBN numbers must be 10 digits long!");
            throw new NumberFormatException("ISBN numbers must be 10 digits long!");
        }

        for (int i = 0; i < 10; i++) {
            int digit = numberOfISBN.charAt(i);

            if (!Character.isDigit(digit)) {
                System.out.println("ISBN numbers must be numeric!");
                throw new NumberFormatException("ISBN numbers must be numeric!");
            }
            total += digit * (10 - i);
        }
        return total % 11 == 0;
    }
}
