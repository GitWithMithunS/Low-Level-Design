package PracticeQuestions.PaymentSystem.factories;

import PracticeQuestions.PaymentSystem.ENUM.GatewayType;
import PracticeQuestions.PaymentSystem.bankingSystem.PaytmbankingSystem;
import PracticeQuestions.PaymentSystem.gateway.PaymentGateway;
import PracticeQuestions.PaymentSystem.gateway.PaymentGatewayProxy;
import PracticeQuestions.PaymentSystem.gateway.PaytmPaymentGateway;
import PracticeQuestions.PaymentSystem.gateway.RazorPayPaymentGateway;

public class GatewayFactory {

    private GatewayFactory() {};

    public static PaymentGateway getPaymentGateway(GatewayType gt){
        if(gt == GatewayType.PAYTM){
            return new PaymentGatewayProxy(new PaytmPaymentGateway() , 3);
        }else if(gt == GatewayType.RAZORPAY){
            return new PaymentGatewayProxy(new RazorPayPaymentGateway() , 5);
        }
        System.out.println("The requested Payment Method is not available");
        return null;
    }

}
