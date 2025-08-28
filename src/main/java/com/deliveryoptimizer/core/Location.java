package com.deliveryoptimizer.core;

/**
 * Represents a geographical location with coordinates and a name
 * Immutable class that serves as a value object for delivery locations
 */
public class Location {
    private final String name;
    private final String address;
    private final double latitude;
    private final double longitude;
    
    public Location(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Convenience constructor for coordinate-first creation
    public Location(double latitude, double longitude, String name) {
        this.name = name;
        this.address = "";
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public String getId() {
        return name;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%.4f, %.4f)", name, latitude, longitude);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return Double.compare(location.latitude, latitude) == 0 &&
               Double.compare(location.longitude, longitude) == 0 &&
               name.equals(location.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
