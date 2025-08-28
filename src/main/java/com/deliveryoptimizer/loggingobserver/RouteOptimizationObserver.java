package com.deliveryoptimizer.loggingobserver;

import com.deliveryoptimizer.core.RouteResult;

/**
 * Observer interface for route optimization events
 * Implements Observer Pattern for monitoring optimization progress
 */
public interface RouteOptimizationObserver {
    
    /**
     * Called when optimization starts
     */
    void onOptimizationStarted(String strategyName, int orderCount);
    
    /**
     * Called when optimization completes
     */
    void onOptimizationCompleted(RouteResult result);
    
    /**
     * Called when optimization progress updates
     */
    void onProgressUpdate(String message, double progressPercentage);
    
    /**
     * Called when an error occurs during optimization
     */
    void onOptimizationError(String errorMessage, Throwable cause);
}
