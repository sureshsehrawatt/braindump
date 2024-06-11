package com.boldbit.core_spring_framework.dependencyInjection.QualifiersUsage;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/di/pay")
public class PaymentController {
    @Autowired
    @Qualifier("creditCard")
    PaymentProcessor paymentProcessor;

    @GetMapping("/payment")
    public String getMethodName() {
        return paymentProcessor.processPayment("200");
    }
}
