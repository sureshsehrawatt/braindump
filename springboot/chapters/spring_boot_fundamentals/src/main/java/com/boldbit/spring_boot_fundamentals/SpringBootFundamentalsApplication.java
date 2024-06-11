package com.boldbit.spring_boot_fundamentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.Lazy.MyLazyBean;

@SpringBootApplication
@ComponentScan(basePackages = "com.boldbit.spring_boot_fundamentals.importantAnotations.Bean.subAnnotations.Lazy")
public class SpringBootFundamentalsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootFundamentalsApplication.class, args);

		// The MyLazyBean should not be initialized at this point
        System.out.println("Spring Boot application started.");

        // Access the MyLazyBean bean to trigger its initialization
        MyLazyBean myLazyBean = context.getBean(MyLazyBean.class);
        myLazyBean.doSomething();
	}

}
