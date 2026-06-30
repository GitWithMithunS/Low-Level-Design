package PracticeQuestions.FoodDeliveryApp.strategies;

public class UpiPaymentStrategy implements PaymentStrategy{
    private String mobile;

    UpiPaymentStrategy(String mobile){
        this.mobile = mobile;
    }

    @Override
    public boolean pay(double amount){
        System.out.println("Paid $" + amount + " using UPI (" + mobile + ")");
        return true;
    }
}
