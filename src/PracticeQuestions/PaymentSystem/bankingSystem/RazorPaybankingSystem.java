package PracticeQuestions.PaymentSystem.bankingSystem;

import java.util.Random;

public class RazorPaybankingSystem implements BankingSystem{

    private final Random rand = new Random();

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Razorpay] Processing payment of " + amount + "...");
        int n = rand.nextInt(100);
        //80% time successful
        return n<=80;
    }

}
