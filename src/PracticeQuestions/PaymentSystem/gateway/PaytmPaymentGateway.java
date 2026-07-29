package PracticeQuestions.PaymentSystem.gateway;


import PracticeQuestions.PaymentSystem.bankingSystem.BankingSystem;
import PracticeQuestions.PaymentSystem.bankingSystem.PaytmbankingSystem;
import PracticeQuestions.PaymentSystem.model.PaymentRequest;

public class PaytmPaymentGateway extends PaymentGateway {
    public PaytmPaymentGateway(){
        super(new PaytmbankingSystem());
    }

    @Override
    protected boolean validatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Validating payment for " + request.getSender() + ".");
        if (request.getAmount() <= 0 || !"INR".equals(request.getCurrency())) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean initiatePayment(PaymentRequest request) {
        System.out.println("[Paytm] Initiating payment of " + request.getAmount()
                + " " + request.getCurrency() + " for " + request.getSender() + ".");
        return bs.processPayment(request.getAmount());
    }

    @Override
    protected boolean confirmPayment(PaymentRequest request) {
        System.out.println("[Paytm] Confirming payment for " + request.getSender() + ".");
        // Confirmation always succeeds in this simulation
        return true;
    }
}
