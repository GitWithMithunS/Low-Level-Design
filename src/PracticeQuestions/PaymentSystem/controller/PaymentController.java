package PracticeQuestions.PaymentSystem.controller;

import PracticeQuestions.PaymentSystem.ENUM.GatewayType;
import PracticeQuestions.PaymentSystem.factories.GatewayFactory;
import PracticeQuestions.PaymentSystem.gateway.PaymentGateway;
import PracticeQuestions.PaymentSystem.model.PaymentRequest;
import PracticeQuestions.PaymentSystem.service.PaymentService;

//Singleton
public class PaymentController {
    private final  PaymentService service;

    private PaymentController(){
        this.service = PaymentService.getInstance();
    };

    private static class Holder{
        private final static PaymentController instance = new PaymentController();
    }

    public static PaymentController getInstance(){
        return Holder.instance;
    }

    public boolean handlePayment(GatewayType type , PaymentRequest req){
        PaymentGateway gateway = GatewayFactory.getPaymentGateway(type);
        service.setGateway(gateway);
        return service.processPayment(req);
    }
}
