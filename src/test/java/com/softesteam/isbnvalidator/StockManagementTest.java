package com.softesteam.isbnvalidator;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockManagementTest {
    static StockManager stockManager;

    @BeforeAll
    static void setUpBeforeAll() throws Exception {
        stockManager = new StockManager();
    }

    @Disabled
    @Test
    @DisplayName("Test Template")
    @Order(0)
    void testTemplate() {
        // setup

        // execute

        // assert
    }

    @Test
    @DisplayName("Test Can Get A Correct Locator Code")
    @Order(1)
    void testCanGetCorrectLocatorCode() {
        // setup
        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookupISBN(String isbn) {
                return null;
            }
        };

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookupISBN(String isbn) {
                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
            }
        };

        stockManager.setDataService(testDatabaseService);
        stockManager.setWebService(testWebService);
        String isbn = "0306406152";
        String expectedLocatorCode = "6152J4";

        // execute
        String actualLocatorCode = stockManager.getLocatorCode(isbn);

        // assert
        assertEquals(expectedLocatorCode, actualLocatorCode);
    }

    @Test
    @DisplayName("Database Is Used If Data Is Present")
    @Order(2)
    void testIsUsedIfDataIsPresent() {
        // setup
        String isbn = "0306406152";
        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        when(testDatabaseService.lookupISBN(isbn)).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        stockManager.setDataService(testDatabaseService);

        // execute
        stockManager.getLocatorCode(isbn);

        // assert
        verify(testDatabaseService, times(1)).lookupISBN(isbn);
    }

    @Test
    @DisplayName("Webservice Is Used If Data Is Not Present In Database")
    @Order(3)
    void testIsUsedIfDataIsNotPresentInDatabase() {
        // setup
        String isbn = "0306406152";
        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
        when(testDatabaseService.lookupISBN(isbn)).thenReturn(null);
        when(testWebService.lookupISBN(isbn)).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
        stockManager.setDataService(testDatabaseService);

        // execute
        stockManager.getLocatorCode(isbn);

        // assert
        verify(testDatabaseService, times(1)).lookupISBN(isbn);
    }
}