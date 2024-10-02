package org.shubhamshankar.payment;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.printf("Paying amount : %f using upi\n" , amount);
    }
}
