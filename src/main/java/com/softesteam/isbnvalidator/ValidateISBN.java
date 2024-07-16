package com.softesteam.isbnvalidator;

public class ValidateISBN {
    public boolean checkISBN(String numberOfISBN) {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += numberOfISBN.charAt(i) * (10 - i);
        }
        return total % 11 == 0;
    }
}
