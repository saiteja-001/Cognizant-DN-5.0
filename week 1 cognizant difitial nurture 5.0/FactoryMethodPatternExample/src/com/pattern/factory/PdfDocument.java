package com.pattern.factory;

/**
 * Concrete Product representing a PDF Document.
 */
public class PdfDocument implements Document {

    @Override
    public void open() {
        System.out.println("[PdfDocument] Opening PDF document: reading cross-reference table, parsing streams, rendering vector graphics.");
    }

    @Override
    public void save() {
        System.out.println("[PdfDocument] Saving PDF document: writing catalog, compressing object streams, finalizing trailer.");
    }

    @Override
    public void close() {
        System.out.println("[PdfDocument] Closing PDF document: releasing canvas context, destroying font descriptors.");
    }
}
