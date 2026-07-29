package PracticeQuestions.PaymentSystem.gateway;

import PracticeQuestions.PaymentSystem.bankingSystem.BankingSystem;
import PracticeQuestions.PaymentSystem.model.PaymentRequest;

public abstract class PaymentGateway {
    protected BankingSystem bs = null;

    public PaymentGateway(BankingSystem bs){
        this.bs = bs;
    }

    public BankingSystem getBs() {
        return bs;
    }

    public boolean processPayment(PaymentRequest request){
        if (!validatePayment(request)) {
            System.out.println("[PaymentGateway] Validation failed for " + request.getSender() + ".");
            return false;
        }
        if (!initiatePayment(request)) {
            System.out.println("[PaymentGateway] Initiation failed for " + request.getSender() + ".");
            return false;
        }
        if (!confirmPayment(request)) {
            System.out.println("[PaymentGateway] Confirmation failed for " + request.getSender() + ".");
            return false;
        }
        return true;
    };

    //to be implemented by the child classes
    protected abstract boolean  validatePayment(PaymentRequest req);
    protected abstract boolean  initiatePayment(PaymentRequest req);
    protected abstract boolean  confirmPayment(PaymentRequest req);

}
