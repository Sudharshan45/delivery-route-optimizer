package com.deliveryoptimizer.core;

/**
 * Represents an action taken during route optimization
 * Used for tracking the optimization process step by step
 */
public class RouteAction {
    private final String actionType;
    private final String description;
    private final double timeAtAction;
    private final Location currentLocation;
    
    public RouteAction(String actionType, String description, double timeAtAction, Location currentLocation) {
        this.actionType = actionType;
        this.description = description;
        this.timeAtAction = timeAtAction;
        this.currentLocation = currentLocation;
    }
    
    public String getActionType() {
        return actionType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getTimeAtAction() {
        return timeAtAction;
    }
    
    public Location getCurrentLocation() {
        return currentLocation;
    }
    
    @Override
    public String toString() {
        return String.format("%.1f min: %s - %s at %s", 
                           timeAtAction, actionType, description, currentLocation.getName());
    }
}
