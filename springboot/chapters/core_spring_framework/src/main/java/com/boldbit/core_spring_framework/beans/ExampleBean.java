package com.boldbit.core_spring_framework.beans;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;

public class ExampleBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private String name;

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name Aware: " + name);
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("Bean Factory Aware");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initializing Bean");
    }

    public void customInit() {
        System.out.println("Custom Init Method");
    }

    @Override
    public void destroy() {
        System.out.println("Disposable Bean");
    }

    public void customDestroy() {
        System.out.println("Custom Destroy Method");
    }
}
