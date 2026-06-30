package PracticeQuestions.FoodDeliveryApp.strategies;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private String cardNumber;
    public CreditCardPaymentStrategy(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount){
        System.out.println("Paid ₹" + amount + " using Credit Card (" + cardNumber + ")");
        return true;
    }
}
