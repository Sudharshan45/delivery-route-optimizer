# Delivery Route Optimizer - Project Structure

## Maven Project Information
- **Group ID**: `com.deliveryoptimizer`
- **Artifact ID**: `delivery-route-optimizer`
- **Version**: `1.0-SNAPSHOT`
- **Java Version**: 17

## Project Overview
This is a comprehensive delivery route optimization system that demonstrates multiple design patterns and algorithms for solving the Traveling Salesman Problem (TSP) in the context of food delivery services.

## Directory Structure

```
delivery-route-optimizer/
├── pom.xml                                    # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/
│       │   │   ├── shortestroute/
│       │   │   │   └── Main.java              # Entry point
│       │   │   └── deliveryoptimizer/
│       │   │       ├── DeliveryOptimizationApp.java    # Main application
│       │   │       ├── DeliveryRouteOptimizerContext.java # Context pattern
│       │   │       ├── calculator/
│       │   │       │   ├── DistanceCalculator.java      # Distance interface
│       │   │       │   └── HaversineDistanceCalculator.java # Haversine impl
│       │   │       ├── domain/
│       │   │       │   ├── Location.java               # Location entity
│       │   │       │   ├── DeliveryOrder.java          # Order entity
│       │   │       │   ├── RouteResult.java            # Result container
│       │   │       │   └── RouteAction.java            # Action tracking
│       │   │       ├── strategy/
│       │   │       │   ├── RouteOptimizationStrategy.java      # Strategy interface
│       │   │       │   ├── BruteForceOptimizationStrategy.java # Optimal solution
│       │   │       │   ├── GreedyNearestNeighborStrategy.java  # Fast approximation
│       │   │       │   └── PriorityBasedOptimizationStrategy.java # Business priority
│       │   │       ├── observer/
│       │   │       │   ├── RouteOptimizationObserver.java      # Observer interface
│       │   │       │   └── ConsoleOptimizationLogger.java      # Console logging
│       │   │       ├── factory/
│       │   │       │   ├── DeliveryScenario.java       # Scenario container
│       │   │       │   └── DeliveryScenarioFactory.java # Scenario factory
│       │   │       └── util/
│       │   │           └── DeliveryDataGenerator.java   # Data generation utility
│       │   └── resources/
│       └── test/
│           └── java/
└── target/                                    # Compiled classes
```

## 🏗️ Package Organization

### `com.shortestroute`
- **Main.java**: Entry point that delegates to the delivery optimization system

### `com.deliveryoptimizer`
- **DeliveryOptimizationApp.java**: Main application demonstrating all features
- **DeliveryRouteOptimizerContext.java**: Context pattern managing strategies

### `com.deliveryoptimizer.domain`
- **Location.java**: Geographical location with coordinates
- **DeliveryOrder.java**: Order with restaurant, consumer, prep time, priority
- **RouteResult.java**: Optimization result container
- **RouteAction.java**: Individual route action tracking

### `com.deliveryoptimizer.strategy` (Strategy Pattern)
- **RouteOptimizationStrategy.java**: Strategy interface
- **BruteForceOptimizationStrategy.java**: O(n!) optimal solution
- **GreedyNearestNeighborStrategy.java**: O(n²) fast approximation  
- **PriorityBasedOptimizationStrategy.java**: O(n log n) business-driven

### `com.deliveryoptimizer.calculator` (Strategy Pattern)
- **DistanceCalculator.java**: Distance calculation interface
- **HaversineDistanceCalculator.java**: Geographical distance implementation

### `com.deliveryoptimizer.observer` (Observer Pattern)
- **RouteOptimizationObserver.java**: Observer interface
- **ConsoleOptimizationLogger.java**: Console logging implementation

### `com.deliveryoptimizer.factory` (Factory Pattern)
- **DeliveryScenario.java**: Scenario container class
- **DeliveryScenarioFactory.java**: Creates predefined test scenarios

### `com.deliveryoptimizer.util`
- **DeliveryDataGenerator.java**: Random data generation for testing

## 🚀 Running the Application

### Compile the Project
```bash
cd delivery-route-optimizer
mvn clean compile
```

### Run the Application
```bash
# Using Maven exec plugin
mvn exec:java

# Or specify main class explicitly
mvn exec:java -Dexec.mainClass="com.deliveryoptimizer.DeliveryOptimizationApp"
```

### Package as JAR
```bash
mvn clean package
java -jar target/delivery-route-optimizer-1.0-SNAPSHOT.jar
```

## 🎯 Key Features

✅ **Clean Architecture**: Proper Maven structure with package separation  
✅ **Design Patterns**: Strategy, Observer, Factory, Context patterns  
✅ **Multiple Algorithms**: Brute force, greedy, priority-based optimization  
✅ **Enterprise Structure**: Professional Java project organization  
✅ **Performance Analysis**: Built-in benchmarking and comparison  
✅ **Extensible Design**: Easy to add new algorithms and features  
✅ **Comprehensive Testing**: Multiple scenarios and edge cases  

## 📊 Demo Output

The application demonstrates:
- Basic scenario optimization with step-by-step tracking
- Strategy comparison showing performance differences
- Complex scenario handling with multiple orders
- Performance analysis comparing execution times
- Real-world business constraint handling

## 🔧 Development

This structure follows Maven conventions and Java best practices:
- Clear separation of concerns
- Proper package naming (reverse domain)
- Design pattern implementation
- Professional project structure
- Extensible architecture

The system is now ready for enterprise deployment and further development! 🎉
