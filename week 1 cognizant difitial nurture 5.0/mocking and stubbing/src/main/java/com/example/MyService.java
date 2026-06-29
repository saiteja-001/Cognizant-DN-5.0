package com.example;

/**
 * Service class that depends on an external API dependency.
 */
public class MyService {
    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /**
     * Fetches data from the external API.
     * 
     * @return Data fetched.
     */
    public String fetchData() {
        return this.externalApi.getData();
    }
}
