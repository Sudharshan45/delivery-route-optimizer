package com.deliveryoptimizer;

import com.deliveryoptimizer.controller.DeliveryRouteOptimizationController;
import com.deliveryoptimizer.optimizationstrategy.ExhaustiveSearchOptimizationStrategy;
import com.deliveryoptimizer.distancestrategy.HaversineDistanceCalculator;
import com.deliveryoptimizer.core.RouteResult;
import com.deliveryoptimizer.loggingobserver.ConsoleOptimizationLogger;
import com.deliveryoptimizer.loggingobserver.RouteOptimizationObserver;
import com.deliveryoptimizer.scenariofactory.DeliveryScenarioFactory;
import com.deliveryoptimizer.scenariofactory.DeliveryScenario;

/**
 * Main application class demonstrating the delivery route optimization system
 * Utilizes multiple design patterns: Strategy, Observer, Factory
 */
public class DeliveryOptimizationApp {
    
    public static void main(String[] args) {
        System.out.println("🚗 Advanced Delivery Route Optimization System 🚗");
        System.out.println("================================================\n");
        
        // Create observer for logging
        RouteOptimizationObserver logger = new ConsoleOptimizationLogger(true);
        
        // Demonstrate basic scenario from problem statement
        demonstrateBasicScenario(logger);
        
        // Demonstrate strategy comparison
        // demonstrateStrategyComparison((ConsoleOptimizationLogger) logger);
        
        System.out.println("\n✅ All demonstrations completed successfully!");
    }
    
    /**
     * Demonstrate the basic scenario from the problem statement
     */
    private static void demonstrateBasicScenario(RouteOptimizationObserver logger) {
        System.out.println("📋 BASIC SCENARIO DEMONSTRATION");
        System.out.println("===============================");
        
        DeliveryScenario scenario = DeliveryScenarioFactory.createBasicScenario();
        System.out.println(scenario);
        
        // Use the new Controller with brute force strategy
        DeliveryRouteOptimizationController controller = new DeliveryRouteOptimizationController(
            new ExhaustiveSearchOptimizationStrategy(),
            new HaversineDistanceCalculator(),
            20.0
        );
        
        // Add observer for monitoring
        controller.addObserver(logger);
        
        // Optimize route using the controller
        RouteResult result = controller.optimizeRoute(scenario.getStartLocation(), 
                                                     scenario.getOrders());
        
        // Calculate additional metrics
    // ...existing code...
    System.out.println();
    }
}
