package com.pattern.factory;

/**
 * Concrete Product representing a Microsoft Word Document.
 */
public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("[WordDocument] Opening Word document: initializing layout, loading fonts, rendering pages.");
    }

    @Override
    public void save() {
        System.out.println("[WordDocument] Saving Word document: writing metadata, packaging XML elements, flushing to disk.");
    }

    @Override
    public void close() {
        System.out.println("[WordDocument] Closing Word document: releasing memory buffers, closing active file handles.");
    }
}
