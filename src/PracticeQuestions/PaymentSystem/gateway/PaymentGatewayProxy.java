package PracticeQuestions.PaymentSystem.gateway;

import PracticeQuestions.PaymentSystem.bankingSystem.BankingSystem;
import PracticeQuestions.PaymentSystem.model.PaymentRequest;

public class PaymentGatewayProxy extends PaymentGateway {
    private final PaymentGateway realGateway;
    private int retries;

    public PaymentGatewayProxy(PaymentGateway pg , int retries){
        this.realGateway = pg;
        this.retries = retries;
        super(pg.getBs());
    }

    public boolean processPayment(PaymentRequest request){
        boolean paymentProcessed = false;
        for (int attempt = 0; attempt < retries; ++attempt) {
            if (attempt > 0) {
                System.out.println("[Proxy] Retrying payment (attempt " + (attempt+1)
                        + ") for " + request.getSender() + ".");
            }
            paymentProcessed = realGateway.processPayment(request);
            if (paymentProcessed) break;
        }
        if (!paymentProcessed) {
            System.out.println("[Proxy] Payment failed after " + retries
                    + " attempts for " + request.getSender() + ".");
        }
        return paymentProcessed;
    }

    @Override
    protected boolean validatePayment(PaymentRequest req) {
        return realGateway.validatePayment(req);
    }

    @Override
    protected boolean initiatePayment(PaymentRequest req) {
        return realGateway.initiatePayment(req);
    }

    @Override
    protected boolean confirmPayment(PaymentRequest req) {
        return realGateway.confirmPayment(req);
    }
}
