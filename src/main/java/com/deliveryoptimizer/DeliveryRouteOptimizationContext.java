package com.deliveryoptimizer;

import java.util.*;

import com.deliveryoptimizer.optimizationstrategy.RouteOptimizationStrategy;
import com.deliveryoptimizer.optimizationstrategy.ExhaustiveSearchOptimizationStrategy;
import com.deliveryoptimizer.distancestrategy.DistanceCalculator;
import com.deliveryoptimizer.distancestrategy.HaversineDistanceCalculator;
import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Location;
import com.deliveryoptimizer.core.RouteResult;
import com.deliveryoptimizer.loggingobserver.RouteOptimizationObserver;

/**
 * Controller class that orchestrates delivery route optimization
 * Uses Strategy pattern for different optimization algorithms
 * Manages business logic and coordinates between components
 */
public class DeliveryRouteOptimizationContext {
    
    private RouteOptimizationStrategy optimizationStrategy;
    private DistanceCalculator distanceCalculator;
    private double averageSpeedKmh;
    private List<RouteOptimizationObserver> observers;
    
    // Default constructor with reasonable defaults
    public DeliveryRouteOptimizationContext() {
    this.optimizationStrategy = new ExhaustiveSearchOptimizationStrategy();
        this.distanceCalculator = new HaversineDistanceCalculator();
        this.averageSpeedKmh = 20.0; // As mentioned in problem statement
        this.observers = new ArrayList<>();
    }
    
    public DeliveryRouteOptimizationContext(RouteOptimizationStrategy optimizationStrategy,
                                             DistanceCalculator distanceCalculator,
                                             double averageSpeedKmh) {
        this.optimizationStrategy = optimizationStrategy;
        this.distanceCalculator = distanceCalculator;
        this.averageSpeedKmh = averageSpeedKmh;
        this.observers = new ArrayList<>();
    }
    
    /**
     * Set the optimization strategy
     */
    public void setOptimizationStrategy(RouteOptimizationStrategy optimizationStrategy) {
        this.optimizationStrategy = optimizationStrategy;
    }
    
    /**
     * Set the distance calculator strategy
     */
    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }
    
    /**
     * Set the average speed for delivery
     */
    public void setAverageSpeedKmh(double averageSpeedKmh) {
        this.averageSpeedKmh = averageSpeedKmh;
    }
    
    /**
     * Add observer for monitoring optimization progress
     */
    public void addObserver(RouteOptimizationObserver observer) {
        this.observers.add(observer);
    }
    
    /**
     * Remove observer
     */
    public void removeObserver(RouteOptimizationObserver observer) {
        this.observers.remove(observer);
    }
    
    /**
     * Find optimal route using current optimization strategy
     */
    public RouteResult optimizeRoute(Location startLocation, List<DeliveryOrder> orders) {
        // Notify observers that optimization is starting
        notifyOptimizationStarted(optimizationStrategy.getStrategyName(), orders.size());
        
        try {
            RouteResult result = optimizationStrategy.optimizeRoute(startLocation, orders, 
                                                                   distanceCalculator, averageSpeedKmh);
            
            // Notify observers that optimization is completed
            notifyOptimizationCompleted(result);
            
            return result;
        } catch (Exception e) {
            // Notify observers of error
            notifyOptimizationError("Optimization failed: " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Compare multiple strategies and return the best result
     * Currently only BruteForce strategy is available
     */
    public List<RouteResult> compareOptimizationStrategies(Location startLocation, List<DeliveryOrder> orders) {
        List<RouteOptimizationStrategy> strategies = Arrays.asList(
            new ExhaustiveSearchOptimizationStrategy()
        );
        
        List<RouteResult> results = new ArrayList<>();
        
        for (RouteOptimizationStrategy strategy : strategies) {
            notifyOptimizationStarted(strategy.getStrategyName(), orders.size());
            
            RouteResult result = strategy.optimizeRoute(startLocation, orders, 
                                                       distanceCalculator, averageSpeedKmh);
            results.add(result);
            
            notifyOptimizationCompleted(result);
        }
        
        // Sort by total time (best first)
        results.sort(Comparator.comparingDouble(RouteResult::getTotalTimeMinutes));
        
        return results;
    }
    
    /**
     * Get the best route among all available strategies
     */
    public RouteResult findBestRoute(Location startLocation, List<DeliveryOrder> orders) {
        List<RouteResult> results = compareOptimizationStrategies(startLocation, orders);
        return results.isEmpty() ? null : results.get(0);
    }
    
    /**
     * Calculate total distance for a specific route
     */
    public double calculateRouteDistance(Location startLocation, List<DeliveryOrder> orders, 
                                       List<String> route) {
        double totalDistance = 0.0;
        
        // Create location map for quick lookup
        Map<String, Location> locationMap = buildLocationMap(startLocation, orders);
        
        // Calculate distance between consecutive points in route
        for (int i = 0; i < route.size() - 1; i++) {
            Location from = locationMap.get(route.get(i));
            Location to = locationMap.get(route.get(i + 1));
            if (from != null && to != null) {
                totalDistance += distanceCalculator.calculateDistance(from, to);
            }
        }
        
        return totalDistance;
    }
    
    /**
     * Helper method to build location map
     */
    private Map<String, Location> buildLocationMap(Location startLocation, List<DeliveryOrder> orders) {
        Map<String, Location> locationMap = new HashMap<>();
        locationMap.put(startLocation.getId(), startLocation);
        
        for (DeliveryOrder order : orders) {
            locationMap.put(order.getRestaurant().getId(), order.getRestaurant());
            locationMap.put(order.getConsumer().getId(), order.getConsumer());
        }
        
        return locationMap;
    }
    
    // Observer notification methods
    private void notifyOptimizationStarted(String strategyName, int orderCount) {
        for (RouteOptimizationObserver observer : observers) {
            observer.onOptimizationStarted(strategyName, orderCount);
        }
    }
    
    private void notifyOptimizationCompleted(RouteResult result) {
        for (RouteOptimizationObserver observer : observers) {
            observer.onOptimizationCompleted(result);
        }
    }
    
    private void notifyOptimizationError(String errorMessage, Throwable cause) {
        for (RouteOptimizationObserver observer : observers) {
            observer.onOptimizationError(errorMessage, cause);
        }
    }
}
