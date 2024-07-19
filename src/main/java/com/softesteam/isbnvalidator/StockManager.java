package com.softesteam.isbnvalidator;

public class StockManager {
    private ExternalISBNDataService webService, dataService;

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDataService(ExternalISBNDataService dataService) {
        this.dataService = dataService;
    }

    public String getLocatorCode(String isbn) {
        Book book = dataService.lookupISBN(isbn);
        if (book == null)
            book = webService.lookupISBN(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0, 1).toUpperCase());
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
