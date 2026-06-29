package com.pattern.factory;

/**
 * Concrete Creator for Word documents.
 */
public class WordDocumentFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
