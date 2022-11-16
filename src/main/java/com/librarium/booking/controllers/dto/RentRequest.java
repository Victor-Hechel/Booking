package com.librarium.booking.controllers.dto;

import java.io.Serializable;

public class RentRequest implements Serializable {

    private String isbn;
    private String legalDocument;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }
}
