package com.softesteam.isbnvalidator;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ValidateISBNTest {

    @Test
    @DisplayName("Check Valid ISBN")
    @Order(1)
    void checkValidISBN() {
        // setup
        String numberOfISBN = "0306406152";
        ValidateISBN validateISBN = new ValidateISBN();

        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertTrue(actual, "The result is should be true");
    }

    @Test
    @DisplayName("Check Valid ISBN")
    @Order(2)
    void checkAnInValidISBN() {
        // setup
        String numberOfISBN = "0306406153";
        ValidateISBN validateISBN = new ValidateISBN();

        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertFalse(actual, "The result is should be false");
    }
}