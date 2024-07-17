package com.softesteam.isbnvalidator;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_MODULO = 10;
    public static final int SHORT_ISBN_MODULO = 11;

    public boolean checkISBN(String numberOfISBN) {
        boolean result = false;

        if (numberOfISBN.length() == LONG_ISBN_LENGTH) {
            return isValidLongISBN(numberOfISBN);
        }
        else if (numberOfISBN.length() == SHORT_ISBN_LENGTH) {
            return isValidShortISBN(numberOfISBN);
        }
        System.out.println("ISBN numbers must be 10 or 13 digits long!");
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long!");
    }

    private static boolean isValidShortISBN(String numberOfISBN) {
        int total = 0;
        int digit;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            char ch = numberOfISBN.charAt(i);

            if (!Character.isDigit(ch)) {
                if (i == 9 && ch == 'X')
                    digit = 10;
                else {
                    System.out.println("All digits of ten-digit ISBN numbers must be numeric!");
                    throw new NumberFormatException("All digits of ten-digit ISBN numbers must be numeric!");
                }
            } else
                digit = Character.getNumericValue(ch);

            total += digit * (SHORT_ISBN_LENGTH - i);
        }

        return total % SHORT_ISBN_MODULO == 0;
    }

    private static boolean isValidLongISBN(String numberOfISBN) {
        int digit;
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            char ch = numberOfISBN.charAt(i);

            if (!Character.isDigit(ch)) {
                System.out.println("All digits of thirteen-digit ISBN numbers must be numeric!");
                throw new NumberFormatException("All digits of thirteen-digit ISBN numbers must be numeric!");
            } else
                digit = Character.getNumericValue(ch);

            if (i % 2 == 0)
                total += digit;
            else
                total += digit * 3;
        }

        return total % LONG_ISBN_MODULO == 0;
    }
}
