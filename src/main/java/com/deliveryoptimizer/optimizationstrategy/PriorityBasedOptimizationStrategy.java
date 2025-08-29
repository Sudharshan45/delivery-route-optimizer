package com.deliveryoptimizer.optimizationstrategy;

import java.util.*;
import com.deliveryoptimizer.core.*;
import com.deliveryoptimizer.distancestrategy.DistanceCalculator;

/**
 * Priority-based optimization strategy
 * Always delivers the highest priority order available
 */
public class PriorityBasedOptimizationStrategy implements RouteOptimizationStrategy {
    @Override
    public RouteResult optimizeRoute(Location startLocation, List<DeliveryOrder> orders, DistanceCalculator distanceCalculator, double speedKmh) {
        List<String> route = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        Set<String> pickedUp = new HashSet<>();
        Set<String> delivered = new HashSet<>();
        Location current = startLocation;
        route.add(current.getName());
        actions.add("Start at " + current.getName());
        double currentTime = 0;
        int n = orders.size();
        while (delivered.size() < n) {
            int nextIdx = -1;
            boolean isPickup = false;
            Location nextLoc = null;
            int maxPriority = -1;
            // Find highest priority available pickup
            for (int i = 0; i < n; i++) {
                DeliveryOrder order = orders.get(i);
                if (!pickedUp.contains(order.getOrderId())) {
                    int priority = order.getPriority().getWeight();
                    if (priority > maxPriority) {
                        maxPriority = priority;
                        nextIdx = i;
                        isPickup = true;
                        nextLoc = order.getRestaurantLocation();
                    }
                }
            }
            // Find highest priority available delivery
            for (int i = 0; i < n; i++) {
                DeliveryOrder order = orders.get(i);
                if (pickedUp.contains(order.getOrderId()) && !delivered.contains(order.getOrderId())) {
                    int priority = order.getPriority().getWeight();
                    if (priority > maxPriority) {
                        maxPriority = priority;
                        nextIdx = i;
                        isPickup = false;
                        nextLoc = order.getConsumerLocation();
                    }
                }
            }
            if (nextIdx == -1) break; // Should not happen
            double travelTime = distanceCalculator.calculateTravelTime(current, nextLoc, speedKmh);
            currentTime += travelTime;
            route.add(nextLoc.getName());
            actions.add(String.format("Travel to %s (%.2f minutes)", nextLoc.getName(), travelTime));
            DeliveryOrder order = orders.get(nextIdx);
            if (isPickup) {
                double foodReadyTime = order.getPreparationTimeMinutes();
                if (currentTime < foodReadyTime) {
                    double waitTime = foodReadyTime - currentTime;
                    currentTime = foodReadyTime;
                    actions.add(String.format("Wait for food preparation (%.1f minutes)", waitTime));
                } else {
                    actions.add("Food is ready - no waiting needed");
                }
                actions.add(String.format("Pick up order %s from %s", order.getOrderId(), nextLoc.getName()));
                pickedUp.add(order.getOrderId());
            } else {
                actions.add(String.format("Deliver order %s to %s", order.getOrderId(), nextLoc.getName()));
                delivered.add(order.getOrderId());
            }
            current = nextLoc;
        }
        return new RouteResult(route, currentTime, getStrategyName(), actions);
    }

    @Override
    public String getStrategyName() {
        return "Priority-Based Optimization";
    }
}
