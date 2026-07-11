# Recursive Algorithms Analysis: Financial Forecasting

This document provides a theoretical and practical analysis of recursive algorithms and details the optimization strategies applied to financial forecasting calculations.

---

## 1. Understanding Recursive Algorithms

### What is Recursion?
**Recursion** is a programming technique where a method calls itself directly or indirectly to solve a problem. It solves a larger problem by breaking it down into smaller, self-similar subproblems of the same type.

Every recursive algorithm requires two fundamental components:
1. **Base Case(s)**: A terminating condition that stops the recursion. Without it, the algorithm would call itself indefinitely, resulting in a stack overflow.
2. **Recursive Step(s)**: The logical step where the problem is reduced to a smaller instance of itself, moving closer to the base case.

### How does recursion simplify certain problems?
- **Mathematical Alignment**: Many problems (e.g., compound interest, factorials, Fibonacci numbers, and tree traversals) are defined recursively in mathematics. Translating these directly to recursive code is intuitive and readable.
- **Divide and Conquer**: Algorithms like Merge Sort and Quick Sort divide problems into independent halves. Recursion naturally handles these branches without needing complex loop state-tracking.
- **State Management**: The compiler/runtime automatically manages the state of each recursive step on the call stack, freeing the programmer from manually managing stack structures.

---

## 2. Complexity Analysis of standard Financial Forecasting Recursion

In our standard recursive implementation:
```java
public static double calculateFutureValueRecursive(double pv, double rate, int periods) {
    if (periods <= 0) {
        return pv;
    }
    return calculateFutureValueRecursive(pv, rate, periods - 1) * (1 + rate);
}
```

### Time Complexity: $O(T)$
- For $T$ periods, the algorithm makes exactly $T$ recursive calls (decrementing `periods` by 1 at each step).
- Each call performs a constant number of operations ($O(1)$) to multiply the result and check the base case.
- Total time complexity: **$O(T)$** (Linear Time).

### Space Complexity: $O(T)$ (Unoptimized)
- Since Java does not natively optimize tail recursion, each recursive call pushes a new frame onto the execution call stack.
- For $T$ periods, there will be $T$ active stack frames at the deepest point of recursion.
- Total space complexity: **$O(T)$** (Linear Space). 
- **Risk**: For a very large time horizon (e.g., $T = 20,000$ days or periods), this will crash the JVM with a `java.lang.StackOverflowError`.

---

## 3. Optimization Strategies

### A. Memoization (Caching)
If the application queries the future values of sub-periods repeatedly (e.g., graphing year-by-year values), standard recursion would run in $O(T^2)$ cumulative time due to redundant computations.
- **Optimization**: Store the result of `calculateFutureValue(T)` in a cache (e.g., an array or hash map).
- **Benefit**: Subperiods are resolved in $O(1)$ time from the cache, maintaining the overall time complexity at $O(T)$ for multiple queries.

### B. Iterative Approach (Preferred for Linear Recursion)
Because future value is a linear recursion problem (unlike the branching recursion of Fibonacci), it can be easily converted to an iterative loop:
```java
public static double calculateFutureValueIterative(double pv, double rate, int periods) {
    double fv = pv;
    for (int i = 0; i < periods; i++) {
        fv *= (1 + rate);
    }
    return fv;
}
```
- **Time Complexity**: **$O(T)$** (Same as recursive).
- **Space Complexity**: **$O(1)$** (Constant space).
- **Benefit**: No stack frames are accumulated, completely eliminating the risk of `StackOverflowError`, making it safe for extremely large horizons.
