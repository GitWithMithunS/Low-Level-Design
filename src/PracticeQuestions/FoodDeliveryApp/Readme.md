src/
└── PracticeQuestions/
└── FoodDeliveryApp/
├── Main.java
├── FoodOrchestrator.java
│
├── ENUM/
│   ├── OrderType.java          
│   └── PaymentStatus.java      
│
├── models/
│   ├── User.java
│   ├── Cart.java
│   ├── Restaurant.java
│   ├── MenuItem.java
│   ├── Order.java              (abstract)
│   ├── DeliveryOrder.java
│   └── PickUpOrder.java
│
├── managers/
│   └── RestaurantManager.java
│
├── services/
│   ├── OrderService.java
│   └── NotificationService.java
│
├── strategies/
│   ├── PaymentStrategy.java    (interface)
│   ├── CreditCardPaymentStrategy.java
│   └── UpiPaymentStrategy.java
│
├── factories/
│   ├── OrderFactory.java       (interface)
│   ├── InstantOrderFactory.java
│   └── ScheduleOrderFactory.java
│
└── utils/
└── TimeUtils.java          (referenced in InstantOrderFactory)