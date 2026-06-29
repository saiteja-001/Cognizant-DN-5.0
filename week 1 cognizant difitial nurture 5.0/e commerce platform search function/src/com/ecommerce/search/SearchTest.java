package com.ecommerce.search;

import java.util.Arrays;
import java.util.Random;

/**
 * Driver class to benchmark Linear Search and Binary Search performance.
 */
public class SearchTest {

    private static final int DATASET_SIZE = 10000;
    private static final int ITERATIONS = 1000; // Run search multiple times to get average times

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      E-commerce Search Benchmarking Tool         ");
        System.out.println("==================================================");

        // 1. Setup products array
        System.out.print("[Setup] Generating " + DATASET_SIZE + " products... ");
        Product[] products = new Product[DATASET_SIZE];
        String[] categories = {"Electronics", "Books", "Clothing", "Home", "Sports"};
        
        for (int i = 0; i < DATASET_SIZE; i++) {
            // Generate product names like "Product 0000", "Product 0001", etc.
            String id = String.format("P%05d", i);
            String name = "Product " + i;
            String category = categories[i % categories.length];
            products[i] = new Product(id, name, category);
        }
        System.out.println("Done.");

        // 2. Prepare sorted array for binary search
        System.out.print("[Setup] Sorting array for binary search... ");
        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts);
        System.out.println("Done.\n");

        // 3. Warm up JVM to stabilize JIT optimization
        System.out.print("[Setup] Warming up JVM... ");
        warmUp(products, sortedProducts);
        System.out.println("Done.\n");

        // 4. Run Benchmarks
        System.out.println("--------------------------------------------------");
        System.out.println("Benchmark 1: Best-Case Search for Linear Search");
        System.out.println("Target: The first element in the array (e.g., 'Product 0')");
        System.out.println("--------------------------------------------------");
        runBenchmark(products, sortedProducts, "Product 0");

        System.out.println("--------------------------------------------------");
        System.out.println("Benchmark 2: Worst-Case Search (Last element)");
        System.out.println("Target: The last element in the array (e.g., 'Product 9999')");
        System.out.println("--------------------------------------------------");
        runBenchmark(products, sortedProducts, "Product 9999");

        System.out.println("--------------------------------------------------");
        System.out.println("Benchmark 3: Average-Case Search (Random elements)");
        System.out.println("--------------------------------------------------");
        runAverageCaseBenchmark(products, sortedProducts);

        System.out.println("--------------------------------------------------");
        System.out.println("Benchmark 4: Worst-Case Search (Non-existent element)");
        System.out.println("Target: 'Product NonExistent'");
        System.out.println("--------------------------------------------------");
        runBenchmark(products, sortedProducts, "Product NonExistent");
    }

    /**
     * Warms up JVM to ensure methods are JIT-compiled for realistic timings.
     */
    private static void warmUp(Product[] products, Product[] sortedProducts) {
        for (int i = 0; i < 5000; i++) {
            SearchAlgorithms.linearSearch(products, "Product 500");
            SearchAlgorithms.binarySearch(sortedProducts, "Product 500");
        }
    }

    /**
     * Runs a benchmark search for a specific target name and prints execution times.
     */
    private static void runBenchmark(Product[] products, Product[] sortedProducts, String targetName) {
        // Measure Linear Search
        long startTime = System.nanoTime();
        Product linearResult = null;
        for (int i = 0; i < ITERATIONS; i++) {
            linearResult = SearchAlgorithms.linearSearch(products, targetName);
        }
        long linearDuration = (System.nanoTime() - startTime) / ITERATIONS; // Average nanoseconds

        // Measure Binary Search
        startTime = System.nanoTime();
        Product binaryResult = null;
        for (int i = 0; i < ITERATIONS; i++) {
            binaryResult = SearchAlgorithms.binarySearch(sortedProducts, targetName);
        }
        long binaryDuration = (System.nanoTime() - startTime) / ITERATIONS; // Average nanoseconds

        printResult("Linear Search", linearResult, linearDuration);
        printResult("Binary Search", binaryResult, binaryDuration);
        System.out.printf("Performance Ratio (Linear/Binary): %.2fx slower%n%n", (double) linearDuration / Math.max(1, binaryDuration));
    }

    /**
     * Runs an average case benchmark using random elements.
     */
    private static void runAverageCaseBenchmark(Product[] products, Product[] sortedProducts) {
        Random rand = new Random(42); // Seeded for reproducibility
        String[] targets = new String[ITERATIONS];
        for (int i = 0; i < ITERATIONS; i++) {
            targets[i] = "Product " + rand.nextInt(DATASET_SIZE);
        }

        // Measure Linear Search
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            SearchAlgorithms.linearSearch(products, targets[i]);
        }
        long linearDuration = (System.nanoTime() - startTime) / ITERATIONS;

        // Measure Binary Search
        startTime = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            SearchAlgorithms.binarySearch(sortedProducts, targets[i]);
        }
        long binaryDuration = (System.nanoTime() - startTime) / ITERATIONS;

        System.out.println("Tested across " + ITERATIONS + " random product lookups.");
        System.out.printf("-> Linear Search (Average-Case): %d ns%n", linearDuration);
        System.out.printf("-> Binary Search (Average-Case): %d ns%n", binaryDuration);
        System.out.printf("Performance Ratio (Linear/Binary): %.2fx slower%n%n", (double) linearDuration / Math.max(1, binaryDuration));
    }

    private static void printResult(String searchType, Product result, long durationNs) {
        String resultStr = (result != null) ? result.toString() : "Not Found";
        System.out.printf("-> %-15s: Found: %-50s | Time: %d ns%n", searchType, resultStr, durationNs);
    }
}
