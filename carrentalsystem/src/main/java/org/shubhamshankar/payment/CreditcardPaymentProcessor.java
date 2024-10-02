package org.shubhamshankar.payment;

public class CreditcardPaymentProcessor implements PaymentProcessor {
    @Override
    public void pay(double amount) {
        System.out.printf("paying amount %f using credit card\n" , amount);
    }
}
