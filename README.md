# 🚗 Delivery Route Optimizer

A comprehensive Java application that solves the delivery route optimization problem using multiple algorithms and design patterns. This project demonstrates enterprise-level software architecture and provides an optimal solution for food delivery route planning.

## 🎯 Problem Statement

Given a delivery agent at a starting location with multiple food orders from different restaurants to different customers, find the optimal route that minimizes total delivery time while considering:
- Food preparation times at restaurants
- Travel distances between locations
- Business priorities (order importance)
- Real-world constraints

## 🏗️ Architecture & Design Patterns

### Design Patterns Implemented
- **Strategy Pattern**: Multiple optimization algorithms
- **Observer Pattern**: Progress monitoring and logging
- **Factory Pattern**: Test scenario generation
- **Context Pattern**: Algorithm coordination


### Algorithm Available
1. **Exhaustive Search Optimization** - O(n!) complexity, optimal solution

## 📋 Features


✅ **Exhaustive Search Optimization** for optimal route calculation  
✅ **Real-time Progress Monitoring** using Observer pattern  
✅ **Comprehensive Logging** with emoji-enhanced output  
✅ **Performance Benchmarking** and execution time analysis  
✅ **Flexible Test Scenarios** via Factory pattern  
✅ **Professional Maven Structure** following Java conventions  
✅ **Enterprise-Ready Architecture** with clean separation of concerns  
✅ **Extensible Design** for adding new algorithms  

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+


### Build and Run
```bash
# Compile the project
mvn clean compile

# Run the application
mvn exec:java

# Or build executable JAR
mvn package
java -jar target/delivery-route-optimizer-1.0-SNAPSHOT.jar
```


## 📊 Sample Output

```
🚗 Advanced Delivery Route Optimization System 🚗
================================================

📋 BASIC SCENARIO DEMONSTRATION
===============================
Scenario: Basic Problem Scenario
Start Location: AMAN
Orders (2):
  Order ORD001: R1 -> C1 (prep: 15 min, priority: MEDIUM)
  Order ORD002: R2 -> C2 (prep: 20 min, priority: HIGH)

🚀 Starting optimization using Exhaustive Search for 2 orders...
✅ Optimization completed: Exhaustive Search Optimization: AMAN -> R1 -> C1 -> R2 -> C2 (20.47 minutes)
```


## 📁 Project Structure

```
delivery-route-optimizer/
├── src/main/java/com/deliveryoptimizer/
│   ├── DeliveryOptimizationApp.java          # Main application
│   ├── DeliveryRouteOptimizationContext.java # Context for optimization
│   ├── constants/                           # Constants and enums
│   │   └── PriorityEnum.java
│   ├── core/                                # Core business entities
│   │   ├── DeliveryOrder.java
│   │   ├── Location.java
│   │   ├── RouteAction.java
│   │   ├── RouteResult.java
│   │   └── Task.java
│   ├── distancestrategy/                    # Distance calculation
│   │   ├── DistanceCalculator.java
│   │   └── HaversineDistanceCalculator.java
│   ├── loggingobserver/                     # Progress monitoring
│   │   ├── RouteOptimizationObserver.java
│   │   └── ConsoleOptimizationLogger.java
│   ├── optimizationstrategy/                # Optimization algorithms
│   │   ├── RouteOptimizationStrategy.java
│   │   └── ExhaustiveSearchOptimizationStrategy.java
│   ├── scenariofactory/                     # Test data generation
│   │   ├── DeliveryScenario.java
│   │   └── DeliveryScenarioFactory.java
│   └── util/                               # Utility classes
│       └── TaskSequenceGenerator.java
├── pom.xml                                  # Maven configuration
├── CLASS_DIAGRAM.md                         # UML documentation
```

## 🎨 UML Class Diagram

See [CLASS_DIAGRAM.md](CLASS_DIAGRAM.md) for detailed class relationships and system architecture visualization using Mermaid diagrams.


## 📈 Performance Analysis

The application includes built-in performance benchmarking:

| Strategy | Time Complexity | Best For |
|----------|----------------|----------|
| Exhaustive Search | O(n!) | Small datasets, optimal solution required |

## 🔧 Extending the System



### Adding New Optimization Strategies
1. Implement `RouteOptimizationStrategy` interface in `optimizationstrategy/`
2. Add your strategy to `DeliveryOptimizationApp` for execution

### Adding New Observers
1. Implement `RouteOptimizationObserver` interface in `loggingobserver/`
2. Register your observer in the application

### Custom Distance Calculators
1. Implement `DistanceCalculator` interface in `distancestrategy/`
2. Update the application to use your calculator

## 📝 Technical Specifications

- **Language**: Java 17
- **Build Tool**: Maven 3.11.0
- **Architecture**: Clean Architecture with Design Patterns
- **Testing**: Comprehensive scenario testing
- **Documentation**: Mermaid UML diagrams

## 🤝 Contributing

This project demonstrates enterprise-level Java development practices:
- Clean code principles
- SOLID design principles
- Design pattern implementation
- Professional project structure
- Comprehensive documentation

## 📄 License

This project is developed for educational and demonstration purposes.

---

## 🎉 Demo Features

The application showcases:
- **Real-world Problem Solving**: Practical delivery optimization
- **Design Pattern Mastery**: Multiple patterns working together
- **Performance Engineering**: Algorithm comparison and benchmarking
- **Enterprise Architecture**: Professional Java project structure
- **Clean Code**: Readable, maintainable, and extensible codebase

Run the application to see the Exhaustive Search algorithm in action with detailed performance analysis! 🚀
