package com.pattern.singleton;

import java.util.Collections;
import java.util.Set;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Test class to verify the Singleton implementation of Logger.
 */
public class SingletonTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Singleton Pattern Verification & Testing       ");
        System.out.println("==================================================");

        testSingleThreadedAccess();
        System.out.println();
        testMultiThreadedAccess();
        System.out.println();
        testLoggingFunctionality();

        System.out.println("==================================================");
        System.out.println("   All tests completed successfully.              ");
        System.out.println("==================================================");
    }

    /**
     * Verifies that multiple references obtained sequentially in a single thread 
     * point to the exact same instance in memory.
     */
    private static void testSingleThreadedAccess() {
        System.out.println("[TEST 1] Testing Single-Threaded Access...");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("Logger 1 reference hash: " + System.identityHashCode(logger1));
        System.out.println("Logger 2 reference hash: " + System.identityHashCode(logger2));

        if (logger1 == logger2) {
            System.out.println("[PASS] logger1 and logger2 refer to the EXACT SAME instance.");
        } else {
            System.out.println("[FAIL] logger1 and logger2 refer to DIFFERENT instances!");
        }
    }

    /**
     * Verifies that under concurrent, multi-threaded access, only a single instance
     * of the Logger is created and shared across all threads.
     */
    private static void testMultiThreadedAccess() {
        System.out.println("[TEST 2] Testing Multi-Threaded Access...");

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(1);
        
        // Use a thread-safe set to collect references retrieved by different threads
        Set<Logger> loggerInstances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    // Block until the latch is released, so all threads start concurrently
                    latch.await();
                    Logger logger = Logger.getInstance();
                    loggerInstances.add(logger);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Release latch to start all threads concurrently
        latch.countDown();

        // Shut down executor and wait for tasks to finish
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Number of unique instances retrieved across " + threadCount + " threads: " + loggerInstances.size());

        if (loggerInstances.size() == 1) {
            System.out.println("[PASS] Only one Logger instance exists across all concurrent threads.");
        } else {
            System.out.println("[FAIL] Multiple Logger instances were created during concurrent execution!");
        }
    }

    /**
     * Verifies that the Logger instance can log messages correctly.
     */
    private static void testLoggingFunctionality() {
        System.out.println("[TEST 3] Testing Logger Functionality...");

        Logger logger = Logger.getInstance();
        
        logger.logInfo("This is an INFO message.");
        logger.logWarning("This is a WARNING message.");
        logger.logError("This is an ERROR message.");

        System.out.println("[PASS] Logger output completed successfully.");
    }
}
