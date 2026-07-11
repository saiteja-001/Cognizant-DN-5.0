package com.ecommerce.search;

/**
 * Utility class containing search algorithms optimized for Product searches.
 */
public class SearchAlgorithms {

    /**
     * Performs a Linear Search to find a product by name.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * @param products The array of products (can be unsorted).
     * @param targetName The product name to search for (case-insensitive).
     * @return The matching Product, or null if not found.
     */
    public static Product linearSearch(Product[] products, String targetName) {
        if (products == null || targetName == null) {
            return null;
        }

        for (Product product : products) {
            if (product != null && product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Performs a Binary Search to find a product by name.
     * The input array MUST be sorted alphabetically by productName.
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     * 
     * @param sortedProducts The sorted array of products.
     * @param targetName The product name to search for (case-insensitive).
     * @return The matching Product, or null if not found.
     */
    public static Product binarySearch(Product[] sortedProducts, String targetName) {
        if (sortedProducts == null || targetName == null) {
            return null;
        }

        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Product midProduct = sortedProducts[mid];
            
            if (midProduct == null) {
                // If there are null holes in the array, adjust bounds
                high--;
                continue;
            }

            int comparison = midProduct.getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return midProduct; // Found!
            } else if (comparison < 0) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }
        return null; // Not found
    }
}
