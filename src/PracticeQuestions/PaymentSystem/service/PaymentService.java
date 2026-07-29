package PracticeQuestions.PaymentSystem.service;

import PracticeQuestions.PaymentSystem.gateway.PaymentGateway;
import PracticeQuestions.PaymentSystem.model.PaymentRequest;

//Singleton
public class PaymentService {
    private PaymentGateway gateway = null;

    private PaymentService(){};

    private static class Holder{
        private static final PaymentService instance = new PaymentService();
    }

    public static PaymentService getInstance(){
        return Holder.instance;
    }

    public boolean processPayment(PaymentRequest req){
        if (gateway == null) {
            System.out.println("[PaymentService] No payment gateway selected.");
            return false;
        }
        return gateway.processPayment(req);
    }

    public void setGateway(PaymentGateway gateway){
        this.gateway = gateway;
    }
}
