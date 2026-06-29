package com.pattern.factory;

/**
 * Concrete Product representing a Microsoft Excel Spreadsheet Document.
 */
public class ExcelDocument implements Document {

    @Override
    public void open() {
        System.out.println("[ExcelDocument] Opening Excel document: parsing worksheets, resolving cell formulas, calculating dependency graph.");
    }

    @Override
    public void save() {
        System.out.println("[ExcelDocument] Saving Excel document: updating cell styles, serializing binary workbook data, writing sheets.");
    }

    @Override
    public void close() {
        System.out.println("[ExcelDocument] Closing Excel document: discarding recalculation caches, releasing sheet tables.");
    }
}
