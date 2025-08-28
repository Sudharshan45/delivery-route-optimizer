# 🏗️ Delivery Route Optimization System - Detailed Class Relationship Diagram

## 📊 UML Class Diagram with Relationships

```mermaid
classDiagram
    %% Domain Entities
    class Location {
        -String name
        -String address
        -double latitude
        -double longitude
        +Location(String, String, double, double)
        +Location(double, double, String)
        +String getName()
        +String getAddress()
        +String getId()
        +double getLatitude()
        +double getLongitude()
        +boolean equals(Object)
        +int hashCode()
        +String toString()
    }

    class DeliveryOrder {
        <<enumeration>> Priority
        -String orderId
        -Location restaurantLocation
        -Location consumerLocation
        -int preparationTimeMinutes
        -Priority priority
        +DeliveryOrder(String, Location, Location, int, Priority)
        +DeliveryOrder(String, Location, Location, int)
        +String getOrderId()
        +Location getRestaurantLocation()
        +Location getConsumerLocation()
        +Location getRestaurant()
        +Location getConsumer()
        +int getPreparationTimeMinutes()
        +Priority getPriority()
        +boolean equals(Object)
        +int hashCode()
        +String toString()
    }

    class Priority {
        <<enumeration>>
        LOW(1)
        MEDIUM(2) 
        HIGH(3)
        -int weight
        +int getWeight()
    }

    class RouteResult {
        -List~String~ route
        -double totalTimeMinutes
        -String strategy
        -List~String~ stepByStepActions
        +RouteResult(List~String~, double, String)
        +RouteResult(List~String~, double, String, List~String~)
        +List~String~ getRoute()
        +double getTotalTimeMinutes()
        +String getStrategy()
        +List~String~ getStepByStepActions()
        +String getRouteAsString()
        +void addAction(String)
        +String toString()
    }

    class RouteAction {
        -String actionType
        -String description
        -double timeAtAction
        -Location currentLocation
        +RouteAction(String, String, double, Location)
        +String getActionType()
        +String getDescription()
        +double getTimeAtAction()
        +Location getCurrentLocation()
        +String toString()
    }

    %% Strategy Pattern
    class RouteOptimizationStrategy {
        <<interface>>
        +RouteResult optimizeRoute(Location, List~DeliveryOrder~, DistanceCalculator, double)
        +String getStrategyName()
    }

    class BruteForceOptimizationStrategy {
        +RouteResult optimizeRoute(Location, List~DeliveryOrder~, DistanceCalculator, double)
        +String getStrategyName()
        -RouteResult calculateRouteTime(Location, List~DeliveryOrder~, List~Integer~, DistanceCalculator, double)
        -List~List~Integer~~ generatePermutations(List~Integer~)
    }

    class GreedyNearestNeighborStrategy {
        +RouteResult optimizeRoute(Location, List~DeliveryOrder~, DistanceCalculator, double)
        +String getStrategyName()
        -DeliveryOrder findNearestOrder(Location, List~DeliveryOrder~, Set~String~, DistanceCalculator)
        -void executeOrder(DeliveryOrder, Location, List~String~, List~String~, DistanceCalculator, double, double)
    }

    class PriorityBasedOptimizationStrategy {
        +RouteResult optimizeRoute(Location, List~DeliveryOrder~, DistanceCalculator, double)
        +String getStrategyName()
    }

    %% Calculator Strategy Pattern
    class DistanceCalculator {
        <<interface>>
        +double calculateDistance(Location, Location)
        +double calculateTravelTime(Location, Location, double)
    }

    class HaversineDistanceCalculator {
        -double EARTH_RADIUS_KM = 6371.0
        +double calculateDistance(Location, Location)
    }

    %% Observer Pattern
    class RouteOptimizationObserver {
        <<interface>>
        +void onOptimizationStarted(String, int)
        +void onOptimizationCompleted(RouteResult)
        +void onProgressUpdate(String, double)
        +void onOptimizationError(String, Throwable)
    }

    class ConsoleOptimizationLogger {
        -boolean verboseMode
        +ConsoleOptimizationLogger()
        +ConsoleOptimizationLogger(boolean)
        +void onOptimizationStarted(String, int)
        +void onOptimizationCompleted(RouteResult)
        +void onProgressUpdate(String, double)
        +void onOptimizationError(String, Throwable)
        +void setVerboseMode(boolean)
    }

    %% Factory Pattern
    class DeliveryScenario {
        -String scenarioName
        -Location startLocation
        -List~DeliveryOrder~ orders
        +DeliveryScenario(String, Location, List~DeliveryOrder~)
        +DeliveryScenario(String, Location, DeliveryOrder[])
        +String getName()
        +Location getStartLocation()
        +List~DeliveryOrder~ getOrders()
        +int getOrderCount()
        +String toString()
    }

    class DeliveryScenarioFactory {
        <<utility>>
        +DeliveryScenario createBasicScenario()$
        +DeliveryScenario createComplexScenario()$
        +DeliveryScenario createEdgeCaseScenario()$
        +DeliveryScenario createPerformanceTestScenario()$
    }

    %% Context and Main Classes
    class DeliveryRouteOptimizerContext {
        -RouteOptimizationStrategy strategy
        -DistanceCalculator distanceCalculator
        -double averageSpeedKmh
        +DeliveryRouteOptimizerContext()
        +DeliveryRouteOptimizerContext(RouteOptimizationStrategy, DistanceCalculator, double)
        +void setStrategy(RouteOptimizationStrategy)
        +RouteResult findOptimalRoute(Location, List~DeliveryOrder~)
        +List~RouteResult~ compareStrategies(Location, List~DeliveryOrder~)
        +double calculateTotalDistance(Location, List~DeliveryOrder~, List~String~)
    }

    class DeliveryOptimizationApp {
        +void main(String[])$
        -void demonstrateBasicScenario(ConsoleOptimizationLogger)$
        -void demonstrateStrategyComparison(ConsoleOptimizationLogger)$
        -void demonstrateComplexScenario(ConsoleOptimizationLogger)$
        -void performanceAnalysis(ConsoleOptimizationLogger)$
    }

    class DeliveryDataGenerator {
        <<utility>>
        -String[] RESTAURANT_NAMES$
        -String[] CONSUMER_NAMES$
        -String[] AREA_NAMES$
        +Location generateRandomLocation()$
        +Location generateRandomLocation(double, double, double, double)$
        +DeliveryOrder generateRandomOrder()$
        +DeliveryOrder[] generateRandomOrders(int, double, double, double)$
        +DeliveryScenario generateCityScenario(int)$
        +DeliveryScenario generatePriorityTestScenario()$
    }

    class Main {
        +void main(String[])$
    }

    %% Relationships
    
    %% Composition and Aggregation
    DeliveryOrder *-- Priority : contains
    DeliveryOrder *-- Location : restaurantLocation
    DeliveryOrder *-- Location : consumerLocation
    RouteAction *-- Location : currentLocation
    DeliveryScenario *-- Location : startLocation
    DeliveryScenario o-- DeliveryOrder : orders
    DeliveryRouteOptimizerContext *-- RouteOptimizationStrategy : strategy
    DeliveryRouteOptimizerContext *-- DistanceCalculator : distanceCalculator

    %% Strategy Pattern Implementation
    RouteOptimizationStrategy <|.. BruteForceOptimizationStrategy : implements
    RouteOptimizationStrategy <|.. GreedyNearestNeighborStrategy : implements
    RouteOptimizationStrategy <|.. PriorityBasedOptimizationStrategy : implements
    DistanceCalculator <|.. HaversineDistanceCalculator : implements

    %% Observer Pattern Implementation
    RouteOptimizationObserver <|.. ConsoleOptimizationLogger : implements

    %% Dependencies and Usage
    BruteForceOptimizationStrategy ..> Location : uses
    BruteForceOptimizationStrategy ..> DeliveryOrder : uses
    BruteForceOptimizationStrategy ..> RouteResult : creates
    BruteForceOptimizationStrategy ..> DistanceCalculator : uses

    GreedyNearestNeighborStrategy ..> Location : uses
    GreedyNearestNeighborStrategy ..> DeliveryOrder : uses
    GreedyNearestNeighborStrategy ..> RouteResult : creates
    GreedyNearestNeighborStrategy ..> DistanceCalculator : uses

    PriorityBasedOptimizationStrategy ..> Location : uses
    PriorityBasedOptimizationStrategy ..> DeliveryOrder : uses
    PriorityBasedOptimizationStrategy ..> RouteResult : creates
    PriorityBasedOptimizationStrategy ..> DistanceCalculator : uses

    HaversineDistanceCalculator ..> Location : uses

    DeliveryScenarioFactory ..> DeliveryScenario : creates
    DeliveryScenarioFactory ..> Location : creates
    DeliveryScenarioFactory ..> DeliveryOrder : creates

    DeliveryDataGenerator ..> Location : creates
    DeliveryDataGenerator ..> DeliveryOrder : creates
    DeliveryDataGenerator ..> DeliveryScenario : creates

    DeliveryOptimizationApp ..> DeliveryRouteOptimizerContext : uses
    DeliveryOptimizationApp ..> DeliveryScenario : uses
    DeliveryOptimizationApp ..> DeliveryScenarioFactory : uses
    DeliveryOptimizationApp ..> ConsoleOptimizationLogger : uses
    DeliveryOptimizationApp ..> BruteForceOptimizationStrategy : uses
    DeliveryOptimizationApp ..> GreedyNearestNeighborStrategy : uses
    DeliveryOptimizationApp ..> PriorityBasedOptimizationStrategy : uses
    DeliveryOptimizationApp ..> HaversineDistanceCalculator : uses

    ConsoleOptimizationLogger ..> RouteResult : uses

    Main ..> DeliveryOptimizationApp : delegates

    %% Return types and method parameters
    RouteOptimizationStrategy ..> RouteResult : returns
    DeliveryRouteOptimizerContext ..> RouteResult : returns
```

## 🎯 Design Pattern Relationships

### 1. **Strategy Pattern** 
```mermaid
classDiagram
    class Context["DeliveryRouteOptimizerContext<br/>📋 Context"]
    class Strategy["RouteOptimizationStrategy<br/>🎯 Strategy Interface"]
    class ConcreteStrategy1["BruteForceOptimizationStrategy<br/>🔍 Concrete Strategy A"]
    class ConcreteStrategy2["GreedyNearestNeighborStrategy<br/>⚡ Concrete Strategy B"]
    class ConcreteStrategy3["PriorityBasedOptimizationStrategy<br/>📊 Concrete Strategy C"]
    
    Context *-- Strategy : has-a
    Strategy <|.. ConcreteStrategy1 : implements
    Strategy <|.. ConcreteStrategy2 : implements  
    Strategy <|.. ConcreteStrategy3 : implements
    
    note for Context "Manages strategy selection<br/>and delegates optimization"
    note for Strategy "Defines optimization<br/>algorithm interface"
    note for ConcreteStrategy1 "O(n!) - Optimal solution<br/>for small datasets"
    note for ConcreteStrategy2 "O(n²) - Fast approximation<br/>for large datasets"
    note for ConcreteStrategy3 "O(n log n) - Business<br/>priority driven"
```

### 2. **Observer Pattern**
```mermaid
classDiagram
    class Subject["DeliveryRouteOptimizerContext<br/>📡 Subject"]
    class Observer["RouteOptimizationObserver<br/>👁️ Observer Interface"]
    class ConcreteObserver["ConsoleOptimizationLogger<br/>🖥️ Concrete Observer"]
    
    Subject o-- Observer : notifies
    Observer <|.. ConcreteObserver : implements
    
    note for Subject "Notifies observers of<br/>optimization events"
    note for Observer "Defines observation<br/>callback interface"
    note for ConcreteObserver "Logs progress and results<br/>to console with emojis"
```

### 3. **Factory Pattern**
```mermaid
classDiagram
    class Factory["DeliveryScenarioFactory<br/>🏭 Factory"]
    class Product["DeliveryScenario<br/>📦 Product"]
    class Component1["Location<br/>📍 Component"]
    class Component2["DeliveryOrder<br/>📋 Component"]
    
    Factory ..> Product : creates
    Product *-- Component1 : contains
    Product *-- Component2 : contains
    
    note for Factory "Creates predefined<br/>test scenarios"
    note for Product "Container for complete<br/>delivery scenario"
    note for Component1 "Geographic locations"
    note for Component2 "Orders with priorities"
```

## 📊 Package Structure & Dependencies

```mermaid
graph TD
    subgraph "🏢 com.shortestroute"
        A[Main.java]
    end
    
    subgraph "🚀 com.deliveryoptimizer"
        B[DeliveryOptimizationApp]
        C[DeliveryRouteOptimizerContext]
    end
    
    subgraph "📋 com.deliveryoptimizer.domain"
        D[Location]
        E[DeliveryOrder]
        F[RouteResult]
        G[RouteAction]
        H[Priority]
    end
    
    subgraph "🎯 com.deliveryoptimizer.strategy"
        I[RouteOptimizationStrategy]
        J[BruteForceOptimizationStrategy]
        K[GreedyNearestNeighborStrategy]
        L[PriorityBasedOptimizationStrategy]
    end
    
    subgraph "📐 com.deliveryoptimizer.calculator"
        M[DistanceCalculator]
        N[HaversineDistanceCalculator]
    end
    
    subgraph "👁️ com.deliveryoptimizer.observer"
        O[RouteOptimizationObserver]
        P[ConsoleOptimizationLogger]
    end
    
    subgraph "🏭 com.deliveryoptimizer.factory"
        Q[DeliveryScenario]
        R[DeliveryScenarioFactory]
    end
    
    subgraph "🛠️ com.deliveryoptimizer.util"
        S[DeliveryDataGenerator]
    end
    
    %% Dependencies
    A --> B
    B --> C
    B --> Q
    B --> R
    B --> P
    C --> I
    C --> M
    I --> D
    I --> E
    I --> F
    J --> I
    K --> I
    L --> I
    N --> M
    P --> O
    E --> H
    E --> D
    F --> D
    G --> D
    Q --> D
    Q --> E
    R --> Q
    S --> D
    S --> E
    S --> Q
```

## 🔍 Detailed Class Interactions

### Core Execution Flow:
1. **Main** → delegates to **DeliveryOptimizationApp**
2. **DeliveryOptimizationApp** → creates scenarios via **DeliveryScenarioFactory**
3. **DeliveryOptimizationApp** → uses **DeliveryRouteOptimizerContext** with different strategies
4. **DeliveryRouteOptimizerContext** → delegates to **RouteOptimizationStrategy** implementations
5. **Strategy implementations** → use **DistanceCalculator** and create **RouteResult**
6. **ConsoleOptimizationLogger** → observes and logs optimization events

### Key Relationships:
- **Composition**: Strong ownership (filled diamond ♦)
- **Aggregation**: Weak ownership (empty diamond ◇)  
- **Implementation**: Interface implementation (dashed line with triangle ⟨⟩)
- **Dependency**: Uses/creates (dashed arrow →)
- **Association**: Direct relationship (solid line —)

This diagram shows the complete architecture with all design patterns, inheritance relationships, dependencies, and data flow through the system! 🎉
