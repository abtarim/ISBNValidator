package com.softesteam.isbnvalidator;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ValidateISBNTest {
    static ValidateISBN validateISBN;

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        validateISBN = new ValidateISBN();
    }

    @Test
    @DisplayName("Check Valid ISBN")
    @Order(1)
    void checkValidISBN() {
        // setup
        String numberOfISBN = "0306406152";

        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertTrue(actual, "The result is should be true");
    }

    @Test
    @DisplayName("Check Invalid ISBN")
    @Order(2)
    void checkAnInvalidISBN() {
        // setup
        String numberOfISBN = "0306406153";

        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertFalse(actual, "The result is should be false");
    }

    @Test
    @DisplayName("Check Invalid Digit Length Not Allowed")
    @Order(3)
    void checkInvalidDigitLengthNotAllowed() {
        // setup
        String numberOfISBN = "030640615";
        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () ->  validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }

    @Test
    @DisplayName("Non Numeric ISBN Not Allowed")
    @Order(4)
    void checkNonNumericISBNNotAllowed() {
        // setup
        String numberOfISBN = "030640615B";

        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () ->  validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }

    @Test
    @DisplayName("Check Valid ISBN Ending X")
    @Order(4)
    void checkValidISBNEndingX() {
        // setup
        String numberOfISBN = "012000030X";
        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertTrue(actual, "The result is should be true");
    }
}