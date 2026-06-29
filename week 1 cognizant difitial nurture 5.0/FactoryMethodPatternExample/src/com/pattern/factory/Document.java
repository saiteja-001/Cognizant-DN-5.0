package com.pattern.factory;

/**
 * Product interface for document types.
 * Defines standard operations that all documents must support.
 */
public interface Document {
    void open();
    void save();
    void close();
}
