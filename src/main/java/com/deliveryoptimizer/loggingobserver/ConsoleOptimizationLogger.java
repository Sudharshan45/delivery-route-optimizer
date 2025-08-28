package com.deliveryoptimizer.loggingobserver;

import com.deliveryoptimizer.core.RouteResult;

/**
 * Console logger implementation of RouteOptimizationObserver
 * Implements Observer Pattern for logging optimization progress to console
 */
public class ConsoleOptimizationLogger implements RouteOptimizationObserver {
    
    private boolean verboseMode;
    
    public ConsoleOptimizationLogger() {
        this.verboseMode = false;
    }
    
    public ConsoleOptimizationLogger(boolean verboseMode) {
        this.verboseMode = verboseMode;
    }
    
    @Override
    public void onOptimizationStarted(String strategyName, int orderCount) {
        System.out.printf("Starting optimization using %s for %d orders...\n", 
                         strategyName, orderCount);
    }
    
    @Override
    public void onOptimizationCompleted(RouteResult result) {
        System.out.printf("Optimization completed: %s\n", result.toString());
        
        if (verboseMode && !result.getStepByStepActions().isEmpty()) {
            System.out.println("Step-by-step actions:");
            for (String action : result.getStepByStepActions()) {
                System.out.println("   • " + action);
            }
        }
    }
    
    @Override
    public void onProgressUpdate(String message, double progressPercentage) {
        if (verboseMode) {
            System.out.printf("⏳ Progress: %.1f%% - %s\n", progressPercentage, message);
        }
    }
    
    @Override
    public void onOptimizationError(String errorMessage, Throwable cause) {
        System.err.printf("❌ Optimization error: %s\n", errorMessage);
        if (cause != null && verboseMode) {
            cause.printStackTrace();
        }
    }
    
    public void setVerboseMode(boolean verboseMode) {
        this.verboseMode = verboseMode;
    }
}
