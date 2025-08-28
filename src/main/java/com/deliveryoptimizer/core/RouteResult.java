package com.deliveryoptimizer.core;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the result of a route optimization
 */
public class RouteResult {
    private final List<String> route;
    private final double totalTimeMinutes;
    private final String strategy;
    private final List<String> stepByStepActions;
    
    public RouteResult(List<String> route, double totalTimeMinutes, String strategy) {
        this.route = new ArrayList<>(route);
        this.totalTimeMinutes = totalTimeMinutes;
        this.strategy = strategy;
        this.stepByStepActions = new ArrayList<>();
    }
    
    public RouteResult(List<String> route, double totalTimeMinutes, String strategy, 
                      List<String> stepByStepActions) {
        this.route = new ArrayList<>(route);
        this.totalTimeMinutes = totalTimeMinutes;
        this.strategy = strategy;
        this.stepByStepActions = new ArrayList<>(stepByStepActions);
    }
    
    public List<String> getRoute() {
        return new ArrayList<>(route);
    }
    
    public double getTotalTimeMinutes() {
        return totalTimeMinutes;
    }
    
    public String getStrategy() {
        return strategy;
    }
    
    public List<String> getStepByStepActions() {
        return new ArrayList<>(stepByStepActions);
    }
    
    public String getRouteAsString() {
        return String.join(" -> ", route);
    }
    
    public void addAction(String action) {
        stepByStepActions.add(action);
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s (%.2f minutes)", 
                strategy, getRouteAsString(), totalTimeMinutes);
    }
}
