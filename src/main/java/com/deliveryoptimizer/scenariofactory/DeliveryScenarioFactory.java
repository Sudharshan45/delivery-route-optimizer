package com.deliveryoptimizer.scenariofactory;

import java.util.Arrays;
import java.util.List;

import com.deliveryoptimizer.constants.PriorityEnum;
import com.deliveryoptimizer.core.DeliveryOrder;
import com.deliveryoptimizer.core.Location;

/**
 * Factory class for creating common delivery scenarios
 * Implements Factory Pattern for easy scenario creation
 */
public class DeliveryScenarioFactory {
    
    /**
     * Create the basic scenario from the problem statement
     */
    public static DeliveryScenario createBasicScenario() {
        // Define locations based on the diagram (Bangalore coordinates as example)
        Location aman = new Location("AMAN", "Aman (Delivery Executive)", 12.9716, 77.5946);
        Location r1 = new Location("R1", "Restaurant 1", 12.9756, 77.5936);
        Location r2 = new Location("R2", "Restaurant 2", 12.9696, 77.5956);
        Location c1 = new Location("C1", "Consumer 1", 12.9776, 77.5926);
        Location c2 = new Location("C2", "Consumer 2", 12.9686, 77.5966);
        
        // Create orders with preparation times as specified
        List<DeliveryOrder> orders = Arrays.asList(
            new DeliveryOrder("ORD001", r1, c1, 15, PriorityEnum.MEDIUM), // pt1 = 15 minutes, normal priority
            new DeliveryOrder("ORD002", r2, c2, 20, PriorityEnum.HIGH)  // pt2 = 20 minutes, high priority
        );
        
        return new DeliveryScenario("Basic Problem Scenario", aman, orders);
    }
    
    /**
     * Create a complex scenario with multiple orders
     */
    public static DeliveryScenario createComplexScenario() {
        Location aman = new Location("AMAN", "Aman (Delivery Executive)", 12.9716, 77.5946);
        
        // Multiple restaurants and consumers
        Location r1 = new Location("R1", "Pizza Palace", 12.9756, 77.5936);
        Location r2 = new Location("R2", "Burger King", 12.9696, 77.5956);
        Location r3 = new Location("R3", "Sushi House", 12.9736, 77.5976);
        Location r4 = new Location("R4", "Taco Bell", 12.9676, 77.5916);
        
        Location c1 = new Location("C1", "Office Complex A", 12.9776, 77.5926);
        Location c2 = new Location("C2", "Residential Area B", 12.9686, 77.5966);
        Location c3 = new Location("C3", "Tech Park C", 12.9796, 77.5906);
        Location c4 = new Location("C4", "Mall D", 12.9656, 77.5986);
        
        List<DeliveryOrder> orders = Arrays.asList(
            new DeliveryOrder("ORD001", r1, c1, 10, PriorityEnum.HIGH), // High priority, quick prep
            new DeliveryOrder("ORD002", r2, c2, 15, PriorityEnum.LOW), // Normal priority
            new DeliveryOrder("ORD003", r3, c3, 25, PriorityEnum.MEDIUM), // Medium priority, slow prep
            new DeliveryOrder("ORD004", r4, c4, 12, PriorityEnum.LOW)  // Normal priority
        );
        
        return new DeliveryScenario("Complex Multi-Order Scenario", aman, orders);
    }
    
    /**
     * Create a scenario for testing edge cases
     */
    public static DeliveryScenario createEdgeCaseScenario() {
        Location aman = new Location("AMAN", "Aman", 0.0, 0.0);
        Location restaurant = new Location("R1", "Same Restaurant", 0.01, 0.01);
        
        // Multiple orders from same restaurant with different prep times
        Location c1 = new Location("C1", "Near Consumer", 0.015, 0.015);
        Location c2 = new Location("C2", "Far Consumer", 0.05, 0.05);
        
        List<DeliveryOrder> orders = Arrays.asList(
            new DeliveryOrder("ORD001", restaurant, c1, 5, PriorityEnum.LOW),  // Quick prep, near delivery
            new DeliveryOrder("ORD002", restaurant, c2, 30, PriorityEnum.LOW)  // Slow prep, far delivery
        );
        
        return new DeliveryScenario("Edge Case - Same Restaurant", aman, orders);
    }
    
    /**
     * Create a performance testing scenario
     */
    public static DeliveryScenario createPerformanceTestScenario() {
        Location aman = new Location("AMAN", "Aman", 12.9716, 77.5946);
        
        // Generate 6 orders for performance testing
        DeliveryOrder[] orders = {
            new DeliveryOrder("ORD001", new Location("R1", "Restaurant 1", 12.9756, 77.5936),
                            new Location("C1", "Consumer 1", 12.9776, 77.5926), 10, PriorityEnum.LOW),
            new DeliveryOrder("ORD002", new Location("R2", "Restaurant 2", 12.9696, 77.5956),
                            new Location("C2", "Consumer 2", 12.9686, 77.5966), 15, PriorityEnum.MEDIUM),
            new DeliveryOrder("ORD003", new Location("R3", "Restaurant 3", 12.9736, 77.5976),
                            new Location("C3", "Consumer 3", 12.9796, 77.5906), 20, PriorityEnum.LOW),
            new DeliveryOrder("ORD004", new Location("R4", "Restaurant 4", 12.9676, 77.5916),
                            new Location("C4", "Consumer 4", 12.9656, 77.5986), 12, PriorityEnum.HIGH),
            new DeliveryOrder("ORD005", new Location("R5", "Restaurant 5", 12.9806, 77.5886),
                            new Location("C5", "Consumer 5", 12.9816, 77.5996), 18, PriorityEnum.LOW),
            new DeliveryOrder("ORD006", new Location("R6", "Restaurant 6", 12.9646, 77.6006),
                            new Location("C6", "Consumer 6", 12.9626, 77.5876), 25, PriorityEnum.MEDIUM)
        };
        
        return new DeliveryScenario("Performance Test - 6 Orders", aman, Arrays.asList(orders));
    }
}
