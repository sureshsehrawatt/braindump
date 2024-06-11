package com.boldbit.core_spring_framework.beans;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class BeanController {

    private final ExampleBean exampleBean;

    public BeanController(ExampleBean exampleBean) {
        this.exampleBean = exampleBean;
    }

    @GetMapping("/beanlifecycle")
    public String getMethodName() {
        return "Check console for bean lifecycle events.";
    }
}
