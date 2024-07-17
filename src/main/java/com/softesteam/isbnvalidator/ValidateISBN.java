package com.softesteam.isbnvalidator;

public class ValidateISBN {
    public boolean checkISBN(String numberOfISBN) {
        int total = 0;
        int digit;

        if (numberOfISBN.length() != 10) {
            System.out.println("ISBN numbers must be 10 digits long!");
            throw new NumberFormatException("ISBN numbers must be 10 digits long!");
        }

        for (int i = 0; i < 10; i++) {
            char ch = numberOfISBN.charAt(i);

            if (!Character.isDigit(ch)) {
                if (i == 9 && ch == 'X')
                    digit = 10;
                else {
                    System.out.println("ISBN numbers must be numeric!");
                    throw new NumberFormatException("ISBN numbers must be numeric!");
                }
            }
            else
                digit = Character.getNumericValue(ch);

            total += digit * (10 - i);
        }

        return total % 11 == 0;
    }
}
