package com.pattern.factory;

/**
 * Test client to demonstrate the Factory Method Design Pattern.
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Factory Method Pattern Verification & Testing  ");
        System.out.println("==================================================");

        // 1. Initialize different factories as the abstract creator type
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // 2. Demonstrate direct creation using the factory method
        System.out.println("[Step 1] Creating documents directly via factory methods...");
        
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        System.out.println("Created wordDoc type: " + wordDoc.getClass().getSimpleName());
        System.out.println("Created pdfDoc type: " + pdfDoc.getClass().getSimpleName());
        System.out.println("Created excelDoc type: " + excelDoc.getClass().getSimpleName());
        System.out.println();

        // 3. Demonstrate execution of methods on the created documents
        System.out.println("[Step 2] Executing document actions manually...");
        wordDoc.open();
        pdfDoc.save();
        excelDoc.close();
        System.out.println();

        // 4. Demonstrate template workflow execution inside the factories
        System.out.println("[Step 3] Executing document lifecycle template workflows...");
        
        System.out.println("--- Word Document Workflow ---");
        wordFactory.processDocument();

        System.out.println("--- PDF Document Workflow ---");
        pdfFactory.processDocument();

        System.out.println("--- Excel Document Workflow ---");
        excelFactory.processDocument();

        System.out.println("==================================================");
        System.out.println("   All tests completed successfully.              ");
        System.out.println("==================================================");
    }
}
