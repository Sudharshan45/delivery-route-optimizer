package com.deliveryoptimizer.optimizationstrategy;

import java.util.*;

import com.deliveryoptimizer.distancestrategy.DistanceCalculator;
import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Location;
import com.deliveryoptimizer.core.RouteResult;
import com.deliveryoptimizer.core.Task;
import com.deliveryoptimizer.util.TaskSequenceGenerator;

/**
 * Exhaustive Search optimization strategy that tests all possible permutations
 * Guarantees optimal solution for small order sets
 */
public class ExhaustiveSearchOptimizationStrategy implements RouteOptimizationStrategy {
    @Override
    public RouteResult optimizeRoute(Location startLocation, List<DeliveryOrder> orders,
                                   DistanceCalculator distanceCalculator, double speedKmh) {
        if (orders.isEmpty()) {
            return new RouteResult(Arrays.asList(startLocation.getId()), 0.0, getStrategyName());
        }
        List<RouteResult> allPossibleRoutes = new ArrayList<>();
        // Generate all possible task sequences (pickup and delivery tasks)
        List<Task> allTasks = TaskSequenceGenerator.generateAllTasks(orders);
        // Generate all valid permutations of tasks (pickup before delivery constraint)
        List<List<Task>> validSequences = TaskSequenceGenerator.generateValidTaskSequences(allTasks);
        for (List<Task> sequence : validSequences) {
            RouteResult result = calculateRouteTimeForTasks(startLocation, orders, sequence,
                                                          distanceCalculator, speedKmh);
            allPossibleRoutes.add(result);
        }
        // Find the route with minimum total time
        return allPossibleRoutes.stream()
                .min(Comparator.comparingDouble(RouteResult::getTotalTimeMinutes))
                .orElse(null);
    }

    @Override
    public String getStrategyName() {
        return "Exhaustive Search Optimization";
    }

    /**
     * Calculate total time for a specific task sequence
     */
    private RouteResult calculateRouteTimeForTasks(Location startLocation, List<DeliveryOrder> orders,
                                                 List<Task> sequence, DistanceCalculator distanceCalculator,
                                                 double speedKmh) {
        List<String> route = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        route.add(startLocation.getName());
        actions.add("Start at " + startLocation.getName());

        double currentTime = 0; // Current time since start
        Location currentLocation = startLocation;

        for (Task task : sequence) {
            DeliveryOrder order = orders.get(task.getOrderIndex());

            // Travel to task location
            double travelTime = distanceCalculator.calculateTravelTime(currentLocation, task.getLocation(), speedKmh);
            currentTime += travelTime;

            route.add(task.getLocation().getName());
            actions.add(String.format("Travel to %s (%.2f minutes)", task.getLocation().getName(), travelTime));

            if (task.isPickup()) {
                // Pickup at restaurant - food preparation started when order was placed (time 0)
                double foodReadyTime = order.getPreparationTimeMinutes();

                if (currentTime < foodReadyTime) {
                    // We arrived early, need to wait for food to be ready
                    double waitTime = foodReadyTime - currentTime;
                    currentTime = foodReadyTime;
                    actions.add(String.format("Wait for food preparation (%.1f minutes)", waitTime));
                } else {
                    // Food is already ready when we arrive
                    actions.add("Food is ready - no waiting needed");
                }

                actions.add(String.format("Pick up order %d from %s", task.getOrderIndex() + 1, task.getLocation().getName()));
            } else {
                // Delivery to customer
                actions.add(String.format("Deliver order %d to %s", task.getOrderIndex() + 1, task.getLocation().getName()));
            }

            currentLocation = task.getLocation();
        }

        return new RouteResult(route, currentTime, getStrategyName(), actions);
    }
}
