package com.deliveryoptimizer.optimizationstrategy;

import java.util.List;

import com.deliveryoptimizer.distancestrategy.DistanceCalculator;
import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Location;
import com.deliveryoptimizer.core.RouteResult;

/**
 * Strategy interface for route optimization algorithms
 * Implements Strategy Pattern for different optimization approaches
 */
public interface RouteOptimizationStrategy {
    
    /**
     * Find the optimal route for given orders
     * @param startLocation Starting location (Aman's position)
     * @param orders List of delivery orders
     * @param distanceCalculator Distance calculation strategy
     * @param speedKmh Average travel speed in km/h
     * @return Optimized route result
     */
    RouteResult optimizeRoute(Location startLocation, List<DeliveryOrder> orders,
                             DistanceCalculator distanceCalculator, double speedKmh);
    
    /**
     * Get the name of this optimization strategy
     * @return Strategy name
     */
    String getStrategyName();
}
