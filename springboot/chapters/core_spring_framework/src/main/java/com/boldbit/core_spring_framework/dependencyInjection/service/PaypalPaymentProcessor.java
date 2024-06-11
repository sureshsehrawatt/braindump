package com.boldbit.core_spring_framework.dependencyInjection.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.boldbit.core_spring_framework.dependencyInjection.QualifiersUsage.PaymentProcessor;

@Component
@Qualifier("paypal")
public class PaypalPaymentProcessor implements PaymentProcessor {

    @Override
    public String processPayment(String str) {
        return "Payal payment: " + str;
    }
}
