package com.deliveryoptimizer.core;

/**
 * Task represents either a pickup or delivery action for route optimization
 * Used to model individual operations in delivery route planning
 */
public class Task {
    private final int orderIndex;
    private final boolean isPickup; // true for pickup (restaurant), false for delivery (customer)
    private final Location location;
    
    public Task(int orderIndex, boolean isPickup, Location location) {
        this.orderIndex = orderIndex;
        this.isPickup = isPickup;
        this.location = location;
    }
    
    public int getOrderIndex() {
        return orderIndex;
    }
    
    public boolean isPickup() {
        return isPickup;
    }
    
    public Location getLocation() {
        return location;
    }
    
    @Override
    public String toString() {
        return (isPickup ? "P" : "D") + orderIndex + ":" + location.getName();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return orderIndex == task.orderIndex && isPickup == task.isPickup;
    }
    
    @Override
    public int hashCode() {
        return orderIndex * 31 + (isPickup ? 1 : 0);
    }
}
