package com.financial.forecasting;

import java.util.Arrays;

/**
 * A Financial Forecasting utility implementing recursive, memoized, and iterative algorithms.
 */
public class FinancialForecasting {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         Financial Forecasting Tool               ");
        System.out.println("==================================================");

        double presentValue = 1000.0; // Initial Investment
        double annualRate = 0.05;     // 5% growth rate
        int years = 50;               // Time horizon

        System.out.printf("Initial Principal: $%.2f%n", presentValue);
        System.out.printf("Growth Rate: %.1f%%%n", annualRate * 100);
        System.out.printf("Forecast Horizon: %d years%n%n", years);

        // 1. Standard Recursive Forecasting
        long startTime = System.nanoTime();
        double recursiveResult = calculateFutureValueRecursive(presentValue, annualRate, years);
        long recursiveDuration = System.nanoTime() - startTime;
        System.out.printf("[Method 1] Standard Recursive Future Value: $%.2f | Time: %d ns%n", 
                recursiveResult, recursiveDuration);

        // 2. Memoized Recursive Forecasting
        // Caching is used here to avoid redundant calculations when querying multiple subperiods
        double[] memo = new double[years + 1];
        Arrays.fill(memo, -1.0);
        startTime = System.nanoTime();
        double memoizedResult = calculateFutureValueMemoized(presentValue, annualRate, years, memo);
        long memoizedDuration = System.nanoTime() - startTime;
        System.out.printf("[Method 2] Memoized Recursive Future Value: $%.2f | Time: %d ns%n", 
                memoizedResult, memoizedDuration);

        // 3. Iterative Forecasting (Space Optimization)
        startTime = System.nanoTime();
        double iterativeResult = calculateFutureValueIterative(presentValue, annualRate, years);
        long iterativeDuration = System.nanoTime() - startTime;
        System.out.printf("[Method 3] Optimized Iterative Future Value: $%.2f | Time: %d ns%n%n", 
                iterativeResult, iterativeDuration);

        // 4. Forecasting with Variable Historical Growth Rates
        double[] pastGrowthRates = {0.04, 0.06, -0.02, 0.05, 0.03}; // Variable rates over 5 periods
        System.out.println("--------------------------------------------------");
        System.out.println("Forecasting with Variable Historical Growth Rates");
        System.out.println("Rates: " + Arrays.toString(pastGrowthRates));
        System.out.println("--------------------------------------------------");
        
        double variableResult = calculateFutureValueVariableRates(presentValue, pastGrowthRates, 0);
        System.out.printf("Forecasted Value after %d periods: $%.2f%n", pastGrowthRates.length, variableResult);
        System.out.println("==================================================");
    }

    /**
     * Standard Recursive Method to calculate future value.
     * Time Complexity: O(T) - T recursive steps
     * Space Complexity: O(T) - due to system call stack frames
     * 
     * Formula: FV = FV(T-1) * (1 + r)
     * 
     * @param pv Current present value.
     * @param rate Periodic growth rate.
     * @param periods Number of periods to forecast.
     * @return Forecasted future value.
     */
    public static double calculateFutureValueRecursive(double pv, double rate, int periods) {
        // Base case: at period 0, future value is equal to the present value
        if (periods <= 0) {
            return pv;
        }
        // Recursive case
        return calculateFutureValueRecursive(pv, rate, periods - 1) * (1 + rate);
    }

    /**
     * Memoized Recursive Method to calculate future value.
     * Prevents re-calculation of intermediate values if called repeatedly.
     * Time Complexity: O(T)
     * Space Complexity: O(T)
     */
    public static double calculateFutureValueMemoized(double pv, double rate, int periods, double[] memo) {
        if (periods <= 0) {
            return pv;
        }

        // Return cached value if it has been calculated before
        if (memo[periods] != -1.0) {
            return memo[periods];
        }

        // Compute and store in cache
        memo[periods] = calculateFutureValueMemoized(pv, rate, periods - 1, memo) * (1 + rate);
        return memo[periods];
    }

    /**
     * Optimized Iterative Method to calculate future value.
     * Solves the Stack Overflow issue of deep recursions.
     * Time Complexity: O(T)
     * Space Complexity: O(1) - Constant auxiliary space
     */
    public static double calculateFutureValueIterative(double pv, double rate, int periods) {
        double fv = pv;
        for (int i = 0; i < periods; i++) {
            fv *= (1 + rate);
        }
        return fv;
    }

    /**
     * Recursive Method to predict future values based on a list of variable growth rates.
     * 
     * @param pv Present value at start of index.
     * @param rates Array of growth rates.
     * @param index Current index in the rates array.
     * @return Forecasted future value.
     */
    public static double calculateFutureValueVariableRates(double pv, double[] rates, int index) {
        // Base Case: when we have applied all growth rates
        if (index >= rates.length) {
            return pv;
        }
        // Recursive Step: apply current growth rate and move to the next index
        double updatedValue = pv * (1 + rates[index]);
        return calculateFutureValueVariableRates(updatedValue, rates, index + 1);
    }
}
