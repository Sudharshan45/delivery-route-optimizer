package com.deliveryoptimizer.distancestrategy;

import com.deliveryoptimizer.core.Location;

/**
 * Strategy interface for distance calculation algorithms
 * Implements Strategy Pattern for different distance calculation methods
 */
public interface DistanceCalculator {
    
    /**
     * Calculate distance between two locations
     * @param from Starting location
     * @param to Destination location
     * @return Distance in kilometers
     */
    double calculateDistance(Location from, Location to);
    
    /**
     * Calculate travel time between two locations
     * @param from Starting location
     * @param to Destination location
     * @param speedKmh Travel speed in km/h
     * @return Travel time in minutes
     */
    default double calculateTravelTime(Location from, Location to, double speedKmh) {
        double distanceKm = calculateDistance(from, to);
        return (distanceKm / speedKmh) * 60; // Convert to minutes
    }
}
