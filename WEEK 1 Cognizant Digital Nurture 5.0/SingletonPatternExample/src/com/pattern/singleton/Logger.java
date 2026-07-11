package com.pattern.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A thread-safe, lazy-initialized Singleton Logger utility class.
 * Uses the Bill Pugh Singleton Design Pattern for high performance and thread-safety
 * without the synchronization overhead of traditional lazy initialization.
 */
public class Logger {

    // DateTime formatter for timestamped logs
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // Private constructor to prevent direct instantiation from outside
    private Logger() {
        // Prevent instantiation via reflection
        if (LoggerHolder.INSTANCE != null) {
            throw new IllegalStateException("Logger instance already created.");
        }
        System.out.println("[Logger SYSTEM] Logger instance initialized successfully.");
    }

    /**
     * Helper class to hold the singleton instance.
     * The class is loaded only when Logger.getInstance() is called,
     * ensuring thread-safe lazy initialization.
     */
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }

    /**
     * Public static method to retrieve the single instance of the Logger class.
     * 
     * @return The single instance of Logger.
     */
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }

    /**
     * Logs an INFO message with a timestamp.
     * 
     * @param message The message to log.
     */
    public void logInfo(String message) {
        log("INFO", message);
    }

    /**
     * Logs a WARNING message with a timestamp.
     * 
     * @param message The message to log.
     */
    public void logWarning(String message) {
        log("WARN", message);
    }

    /**
     * Logs an ERROR message with a timestamp.
     * 
     * @param message The message to log.
     */
    public void logError(String message) {
        log("ERROR", message);
    }

    /**
     * Private helper method to print formatted log messages to console.
     */
    private void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        System.out.printf("[%s] [%s] %s%n", timestamp, level, message);
    }
}
