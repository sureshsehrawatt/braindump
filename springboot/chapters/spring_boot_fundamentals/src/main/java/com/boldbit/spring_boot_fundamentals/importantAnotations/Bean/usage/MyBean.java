package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.usage;

import org.springframework.stereotype.Component;

@Component
public class MyBean {
    public MyBean() {
        //TODO Auto-generated constructor stub
    }
    
    public MyBean(MyBean repository) {
        //TODO Auto-generated constructor stub
    }

    public void doSomthing(){
        System.out.println("doing...");
    }
}
