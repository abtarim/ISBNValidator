package com.softesteam.isbnvalidator;

public class ValidateISBN {
    public boolean checkISBN(String numberOfISBN) {
        int[] digitsOfISBN = new int[10];
        for (int i = 0; i < numberOfISBN.length(); i++) {
            String s = numberOfISBN.substring(i, i + 1);
            digitsOfISBN[i] = Integer.parseInt(s);
        }
        return isValidISBN(digitsOfISBN) == 0;
    }

    // Returns ISBN error syndrome, zero for a valid ISBN, non-zero for an invalid one.
    // digits[i] must be between 0 and 10.
    int isValidISBN(int[] digits) {
        int i, s = 0, t = 0;

        for (i = 0; i < 10; ++i) {
            t += digits[i];
            s += t;
        }
        return s % 11;
    }
}
