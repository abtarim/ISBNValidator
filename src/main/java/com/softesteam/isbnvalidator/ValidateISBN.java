package com.softesteam.isbnvalidator;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_MODULO = 10;
    public static final int SHORT_ISBN_MODULO = 11;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isValidLongISBN(isbn);
        }
        else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isValidShortISBN(isbn);
        }
        System.out.println("ISBN numbers must be 10 or 13 digits long!");
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long!");
    }

    private static boolean isValidShortISBN(String isbn) {
        int total = 0;
        int digit;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            char charOfISBN = isbn.charAt(i);

            if (!Character.isDigit(charOfISBN)) {
                if (i == 9 && charOfISBN == 'X')
                    digit = 10;
                else {
                    System.out.println("All digits of ten-digit ISBN numbers must be numeric!");
                    throw new NumberFormatException("All digits of ten-digit ISBN numbers must be numeric!");
                }
            } else
                digit = Character.getNumericValue(charOfISBN);

            total += digit * (SHORT_ISBN_LENGTH - i);
        }

        return total % SHORT_ISBN_MODULO == 0;
    }

    private static boolean isValidLongISBN(String isbn) {
        int digit;
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            char charOfISBN = isbn.charAt(i);

            if (!Character.isDigit(charOfISBN)) {
                System.out.println("All digits of thirteen-digit ISBN numbers must be numeric!");
                throw new NumberFormatException("All digits of thirteen-digit ISBN numbers must be numeric!");
            } else
                digit = Character.getNumericValue(charOfISBN);

            if (i % 2 == 0)
                total += digit;
            else
                total += digit * 3;
        }

        return total % LONG_ISBN_MODULO == 0;
    }
}
