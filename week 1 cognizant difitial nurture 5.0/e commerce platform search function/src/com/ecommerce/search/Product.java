package com.ecommerce.search;

import java.util.Objects;

/**
 * Represents a Product in the e-commerce platform.
 * Implements Comparable to allow sorting by product name for binary search.
 */
public class Product implements Comparable<Product> {
    private final String productId;
    private final String productName;
    private final String category;

    public Product(String productId, String productName, String category) {
        this.productId = Objects.requireNonNull(productId, "Product ID cannot be null");
        this.productName = Objects.requireNonNull(productName, "Product name cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int compareTo(Product other) {
        // Compare case-insensitively for cleaner e-commerce search ordering
        return this.productName.compareToIgnoreCase(other.productName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return String.format("Product{ID='%s', Name='%s', Category='%s'}", productId, productName, category);
    }
}
