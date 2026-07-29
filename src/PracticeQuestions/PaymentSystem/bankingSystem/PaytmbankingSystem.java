package PracticeQuestions.PaymentSystem.bankingSystem;

import java.util.Random;

public class PaytmbankingSystem implements BankingSystem{

    private final Random rand = new Random();

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Paytm] Processing payment of " + amount + "...");
        int n = rand.nextInt(100);
        //65% time successful
        return n<=65;
    }

}
