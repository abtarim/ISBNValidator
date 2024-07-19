package com.softesteam.isbnvalidator;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockManagementTest {
    private ExternalISBNDataService testDatabaseService;
    private ExternalISBNDataService testWebService;
    private StockManager stockManager;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testDatabaseService = mock(ExternalISBNDataService.class);
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setDataService(testDatabaseService);
        stockManager.setWebService(testWebService);
    }

    @Test
    @DisplayName("Test Can Get A Correct Locator Code")
    @Order(1)
    void testCanGetCorrectLocatorCode() {
        // setup
        String isbn = "0306406152";
        String expectedLocatorCode = "6152J4";
        testBook = new Book(isbn, "Of Mice And Men", "J. Steinbeck");

        when(testDatabaseService.lookupISBN(anyString())).thenReturn(null);
        when(testWebService.lookupISBN(anyString())).thenReturn(testBook);

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
        testBook = new Book(isbn, "Of Mice And Men", "J. Steinbeck");
        when(testDatabaseService.lookupISBN(isbn)).thenReturn(testBook);

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
        testBook = new Book(isbn, "Of Mice And Men", "J. Steinbeck");
        when(testDatabaseService.lookupISBN(isbn)).thenReturn(null);
        when(testWebService.lookupISBN(isbn)).thenReturn(testBook);

        // execute
        stockManager.getLocatorCode(isbn);

        // assert
        verify(testDatabaseService, times(1)).lookupISBN(isbn);
    }
}