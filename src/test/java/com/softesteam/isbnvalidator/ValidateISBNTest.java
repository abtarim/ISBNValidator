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
    @DisplayName("Check Valid Ten Digit ISBN")
    @Order(1)
    void checkValidTenDigitISBN() {
        // setup
        String isbn = "0306406152";

        // execute
        boolean actual = validateISBN.checkISBN(isbn);

        // assert
        assertTrue(actual, "The result is should be true");
    }

    @Test
    @DisplayName("Check Invalid Ten Digit ISBN")
    @Order(2)
    void checkAnInvalidTenDigitISBN() {
        // setup
        String isbn = "0306406153";

        // execute
        boolean actual = validateISBN.checkISBN(isbn);

        // assert
        assertFalse(actual, "The result is should be false");
    }

    @Test
    @DisplayName("Check Valid Thirteen Digit ISBN")
    @Order(3)
    void checkValidThirteenDigitISBN() {
        // setup
        String isbn = "9780306406157";

        // execute
        boolean actual = validateISBN.checkISBN(isbn);

        // assert
        assertTrue(actual, "The result is should be true");
    }

    @Test
    @DisplayName("Check Invalid Thirteen Digit ISBN")
    @Order(4)
    void checkInvalidThirteenDigitISBN() {
        // setup
        String isbn = "9780306406156";

        // execute
        boolean actual = validateISBN.checkISBN(isbn);

        // assert
        assertFalse(actual, "The result is should be false");
    }

    @Test
    @DisplayName("Check Invalid Ten Digit Length Not Allowed")
    @Order(5)
    void checkInvalidTenDigitLengthNotAllowed() {
        // setup
        String numberOfISBN = "030640615";
        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () -> validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }


    @Test
    @DisplayName("Check Invalid Thirteen Digit Length Not Allowed")
    @Order(6)
    void checkInvalidThirteenDigitLengthNotAllowed() {
        // setup
        String numberOfISBN = "978030640615";
        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () -> validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }

    @Test
    @DisplayName("Non Numeric Ten Digit ISBN Not Allowed")
    @Order(7)
    void checkNonNumericTenDigitISBNNotAllowed() {
        // setup
        String numberOfISBN = "030640615B";
        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () -> validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }

    @Test
    @DisplayName("Non Numeric Thirteen Digit ISBN Not Allowed")
    @Order(8)
    void checkNonNumericThirteenDigitISBNNotAllowed() {
        // setup
        String numberOfISBN = "978030640615B";
        Class<NumberFormatException> expectedException = NumberFormatException.class;

        // execute
        Executable actualException = () -> validateISBN.checkISBN(numberOfISBN);

        // assert
        assertThrows(expectedException, actualException);
    }

    @Test
    @DisplayName("Check Valid ISBN Ending X")
    @Order(9)
    void checkValidISBNEndingX() {
        // setup
        String numberOfISBN = "012000030X";

        // execute
        boolean actual = validateISBN.checkISBN(numberOfISBN);

        // assert
        assertTrue(actual, "The result is should be true");
    }
}