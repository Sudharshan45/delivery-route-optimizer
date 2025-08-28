package com.deliveryoptimizer.util;

import java.util.*;

import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Task;

/**
 * Utility class for generating task sequences for batch delivery optimization
 * Provides methods to create and validate pickup/delivery task combinations
 */
public class TaskSequenceGenerator {
    
    /**
     * Generate all pickup and delivery tasks for the orders
     */
    public static List<Task> generateAllTasks(List<DeliveryOrder> orders) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            DeliveryOrder order = orders.get(i);
            tasks.add(new Task(i, true, order.getRestaurantLocation()));  // Pickup task
            tasks.add(new Task(i, false, order.getConsumerLocation()));   // Delivery task
        }
        return tasks;
    }
    
    /**
     * Generate all valid task sequences ensuring pickup happens before delivery for each order
     */
    public static List<List<Task>> generateValidTaskSequences(List<Task> allTasks) {
        List<List<Task>> result = new ArrayList<>();
        generateValidSequencesRecursive(allTasks, new ArrayList<>(), new boolean[allTasks.size() / 2], result);
        return result;
    }
    
    /**
     * Recursive helper to generate valid sequences
     */
    private static void generateValidSequencesRecursive(List<Task> allTasks, List<Task> currentSequence, 
                                               boolean[] pickedUp, List<List<Task>> result) {
        if (currentSequence.size() == allTasks.size()) {
            result.add(new ArrayList<>(currentSequence));
            return;
        }
        
        for (Task task : allTasks) {
            if (currentSequence.contains(task)) continue;
            
            // Check if this task can be added
            if (task.isPickup() || pickedUp[task.getOrderIndex()]) {
                // For pickup: can always be added
                // For delivery: can only be added if already picked up
                
                boolean[] newPickedUp = pickedUp.clone();
                if (task.isPickup()) {
                    newPickedUp[task.getOrderIndex()] = true;
                }
                
                currentSequence.add(task);
                generateValidSequencesRecursive(allTasks, currentSequence, newPickedUp, result);
                currentSequence.remove(currentSequence.size() - 1);
            }
        }
    }
}
