package com.deliveryoptimizer.scenariofactory;

import java.util.List;

import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Location;

import java.util.ArrayList;

/**
 * Represents a complete delivery scenario with starting location and orders
 * Encapsulates all data needed for route optimization
 */
public class DeliveryScenario {
    private final String scenarioName;
    private final Location startLocation;
    private final List<DeliveryOrder> orders;
    
    public DeliveryScenario(String name, Location startLocation, List<DeliveryOrder> orders) {
        this.scenarioName = name;
        this.startLocation = startLocation;
        this.orders = new ArrayList<>(orders);
    }
    
    // Convenience constructor for arrays
    public DeliveryScenario(String name, Location startLocation, DeliveryOrder[] ordersArray) {
        this.scenarioName = name;
        this.startLocation = startLocation;
        this.orders = new ArrayList<>();
        for (DeliveryOrder order : ordersArray) {
            this.orders.add(order);
        }
    }
    
    public String getScenarioName() {
        return scenarioName;
    }
    
    public Location getStartLocation() {
        return startLocation;
    }
    
    public List<DeliveryOrder> getOrders() {
        return new ArrayList<>(orders);
    }
    
    public int getOrderCount() {
        return orders.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scenario: ").append(scenarioName).append("\n");
        sb.append("Start Location: ").append(startLocation.getName()).append("\n");
        sb.append("Orders (").append(orders.size()).append("):\n");
        
        for (DeliveryOrder order : orders) {
            sb.append("  ").append(order.toString()).append("\n");
        }
        
        return sb.toString();
    }
}
