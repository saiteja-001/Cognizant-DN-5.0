package com.example;

/**
 * Interface representing an external API dependency.
 */
public interface ExternalApi {
    /**
     * Fetches data from the external source.
     * 
     * @return String data.
     */
    String getData();
}
