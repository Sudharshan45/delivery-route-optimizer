package com.deliveryoptimizer.core;

import com.deliveryoptimizer.constants.PriorityEnum;

/**
 * Represents a delivery order with restaurant, consumer, and priority information
 * Contains all necessary data for route optimization
 */
public class DeliveryOrder {
    
    private final String orderId;
    private final Location restaurantLocation;
    private final Location consumerLocation;
    private final int preparationTimeMinutes;
    private final PriorityEnum priority;
    
    public DeliveryOrder(String orderId, Location restaurantLocation, Location consumerLocation, 
                        int preparationTimeMinutes, PriorityEnum priority) {
        this.orderId = orderId;
        this.restaurantLocation = restaurantLocation;
        this.consumerLocation = consumerLocation;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.priority = priority;
    }
    
    // Convenience constructor with default medium priority
    public DeliveryOrder(String orderId, Location restaurantLocation, Location consumerLocation, 
                        int preparationTimeMinutes) {
        this(orderId, restaurantLocation, consumerLocation, preparationTimeMinutes, PriorityEnum.MEDIUM);
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public Location getRestaurantLocation() {
        return restaurantLocation;
    }
    
    public Location getConsumerLocation() {
        return consumerLocation;
    }
    
    public int getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }
    
    public PriorityEnum getPriority() {
        return priority;
    }
    
    // Convenience methods for backwards compatibility
    public Location getRestaurant() {
        return restaurantLocation;
    }
    
    public Location getConsumer() {
        return consumerLocation;
    }
    
    @Override
    public String toString() {
        return String.format("Order %s: %s -> %s (prep: %d min, priority: %s)",
                           orderId, restaurantLocation.getName(), consumerLocation.getName(),
                           preparationTimeMinutes, priority);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        DeliveryOrder that = (DeliveryOrder) obj;
        return orderId.equals(that.orderId);
    }
    
    @Override
    public int hashCode() {
        return orderId.hashCode();
    }
}
