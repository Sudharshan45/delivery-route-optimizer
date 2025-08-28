package com.deliveryoptimizer.distancestrategy;

import com.deliveryoptimizer.core.Location;

/**
 * Haversine formula implementation for calculating distances between geo-coordinates
 * Implements Strategy Pattern for distance calculation
 */
public class HaversineDistanceCalculator implements DistanceCalculator {
    
    private static final double EARTH_RADIUS_KM = 6371.0;
    
    @Override
    public double calculateDistance(Location from, Location to) {
        double lat1Rad = Math.toRadians(from.getLatitude());
        double lat2Rad = Math.toRadians(to.getLatitude());
        double deltaLat = Math.toRadians(to.getLatitude() - from.getLatitude());
        double deltaLng = Math.toRadians(to.getLongitude() - from.getLongitude());
        
        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(deltaLng/2) * Math.sin(deltaLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return EARTH_RADIUS_KM * c;
    }
}
