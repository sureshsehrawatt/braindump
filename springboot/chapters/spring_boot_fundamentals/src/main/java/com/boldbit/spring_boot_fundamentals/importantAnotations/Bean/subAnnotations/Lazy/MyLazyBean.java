package com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.Lazy;

public class MyLazyBean {

    public MyLazyBean() {
        System.out.println("MyLazyBean initialized!");
    }

    public void doSomething() {
        System.out.println("MyLazyBean doing something...");
    }
}
