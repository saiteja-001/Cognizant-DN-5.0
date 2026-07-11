package com.pattern.factory;

/**
 * Concrete Creator for Excel documents.
 */
public class ExcelDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
