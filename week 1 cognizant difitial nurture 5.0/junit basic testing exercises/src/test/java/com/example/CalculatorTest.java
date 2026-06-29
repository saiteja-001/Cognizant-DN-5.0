package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Calculator class.
 * Demonstrates basic JUnit 4 assertions and lifecycle annotations.
 */
public class CalculatorTest {

    private Calculator calculator;

    /**
     * Executes before every individual test case.
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("[Lifecycle] Setup: Initialized new Calculator instance.");
    }

    /**
     * Executes after every individual test case.
     */
    @After
    public void tearDown() {
        calculator = null;
        System.out.println("[Lifecycle] TearDown: Cleaned up Calculator instance.");
    }

    /**
     * Tests the addition operation.
     */
    @Test
    public void testAdd() {
        assertEquals("Adding 2 + 3 should equal 5", 5, calculator.add(2, 3));
        assertEquals("Adding -2 + 1 should equal -1", -1, calculator.add(-2, 1));
        assertEquals("Adding 0 + 0 should equal 0", 0, calculator.add(0, 0));
        System.out.println("-> testAdd passed.");
    }

    /**
     * Tests the subtraction operation.
     */
    @Test
    public void testSubtract() {
        assertEquals("Subtracting 2 - 3 should equal -1", -1, calculator.subtract(2, 3));
        assertEquals("Subtracting -2 - 1 should equal -3", -3, calculator.subtract(-2, 1));
        assertEquals("Subtracting 5 - 0 should equal 5", 5, calculator.subtract(5, 0));
        System.out.println("-> testSubtract passed.");
    }

    /**
     * Tests the multiplication operation.
     */
    @Test
    public void testMultiply() {
        assertEquals("Multiplying 2 * 3 should equal 6", 6, calculator.multiply(2, 3));
        assertEquals("Multiplying -2 * 4 should equal -8", -8, calculator.multiply(-2, 4));
        assertEquals("Multiplying 5 * 0 should equal 0", 0, calculator.multiply(5, 0));
        System.out.println("-> testMultiply passed.");
    }

    /**
     * Tests the division operation.
     */
    @Test
    public void testDivide() {
        assertEquals("Dividing 6 / 3 should equal 2.0", 2.0, calculator.divide(6, 3), 0.0001);
        assertEquals("Dividing -5 / 2 should equal -2.5", -2.5, calculator.divide(-5, 2), 0.0001);
        System.out.println("-> testDivide passed.");
    }

    /**
     * Tests that dividing by zero throws the expected IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        System.out.println("-> Attempting division by zero (expecting exception)...");
        calculator.divide(5, 0);
    }
}
