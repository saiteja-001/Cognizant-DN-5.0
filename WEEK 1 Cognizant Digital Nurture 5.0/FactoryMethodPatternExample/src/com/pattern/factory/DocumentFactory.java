package com.pattern.factory;

/**
 * Creator abstract class in the Factory Method Pattern.
 * Declares the factory method to create a Document.
 * Also contains core business logic that relies on the Document interface.
 */
public abstract class DocumentFactory {

    /**
     * The Factory Method. Concrete subclasses must implement this method
     * to return their respective concrete Document instances.
     * 
     * @return A concrete Document instance.
     */
    public abstract Document createDocument();

    /**
     * Helper template method demonstrating core business logic.
     * It uses the factory method to instantiate a document and process it.
     */
    public void processDocument() {
        System.out.println("[Factory Log] Preparing document lifecycle processing...");
        
        // Instantiate using the factory method - subclass decides the class!
        Document doc = createDocument();
        
        // Use the product
        doc.open();
        doc.save();
        doc.close();
        
        System.out.println("[Factory Log] Document processing lifecycle finished.\n");
    }
}
