package com.pattern.factory;

/**
 * Concrete Creator for PDF documents.
 */
public class PdfDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
