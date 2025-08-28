package com.deliveryoptimizer.constants;

/**
 * Priority levels for delivery orders
 * Common enum used across the delivery optimization system
 */
public enum PriorityEnum {
    LOW(1), MEDIUM(2), HIGH(3);
    
    private final int weight;
    
    PriorityEnum(int weight) {
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return name();
    }
}
