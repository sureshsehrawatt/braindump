package com.boldbit.core_spring_framework.dependencyInjection.QualifiersUsage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("creditCard")
public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public String processPayment(String str) {
        return "CreditCard payment: " + str;
    }
}
