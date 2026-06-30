package PracticeQuestions.FoodDeliveryApp.services;


import PracticeQuestions.FoodDeliveryApp.ENUM.OrderType;
import PracticeQuestions.FoodDeliveryApp.ENUM.PaymentStatus;
import PracticeQuestions.FoodDeliveryApp.factories.InstantOrderFactory;
import PracticeQuestions.FoodDeliveryApp.factories.OrderFactory;
import PracticeQuestions.FoodDeliveryApp.factories.ScheduleOrderFactory;
import PracticeQuestions.FoodDeliveryApp.models.*;
import PracticeQuestions.FoodDeliveryApp.strategies.PaymentStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    private static class Holder{
        private static final OrderService instance = new OrderService();
    }

    private OrderService(){}

    public static OrderService getInstance(){
        return Holder.instance;
    }

    public Order checkoutNow(User user, OrderType orderType, PaymentStrategy paymentStrategy) {
        return checkout(user, orderType, paymentStrategy, new InstantOrderFactory());
    }

    public Order checkoutScheduled(User user, OrderType orderType, PaymentStrategy paymentStrategy, String scheduleTime) {
        return checkout(user, orderType, paymentStrategy, new ScheduleOrderFactory(scheduleTime));
    }

    public Order checkout(User user , OrderType orderType , PaymentStrategy paymentStrategy , OrderFactory orderFactory){
        Cart cart = user.getCart();
        if(cart.isEmpty())
            throw new IllegalStateException("Cart is Empty");

        //getting a copy of all cart items for creating a snapshot for order class
        Map<MenuItem , Integer> items = new HashMap<>(cart.getItems());
        Restaurant restaurant = cart.getRestaurant();
        double cost = cart.getCost();

        //create an order for user's cart
        Order order = orderFactory.createOrder(user , restaurant , items , cost , orderType);
        order.setPaymentStatus(PaymentStatus.PENDING);

        //complete payment
        boolean paymentSuccess = processPayment(paymentStrategy , order);

        //clear cart of the user as order is placed and payment completed
        if(paymentSuccess) {
            order.setPaymentStatus(PaymentStatus.SUCCESS);
            cart.clearCart();
            orders.add(order);
            //notify the user
            notifyOrder(order);
        }

        return order;
    }

    private void notifyOrder(Order order) {
        NotificationService.sendNotification(order);
    }

    private boolean processPayment(PaymentStrategy paymentStrategy, Order order) {
        boolean success = paymentStrategy.pay(order.getCost());
        if(!success){
            order.setPaymentStatus(PaymentStatus.FAILED);
            throw new RuntimeException("Payment Failed.");
        }
        return true;
    }

    public void cancelOrder(Order order){
        if(order.getPaymentStatus()==PaymentStatus.SUCCESS)
            throw new IllegalStateException("Order cannot be cancelled after successful payment.");

        order.setPaymentStatus(PaymentStatus.FAILED);
    }


    public List<Order> getAllOrders(){
        return orders;
    }

}
