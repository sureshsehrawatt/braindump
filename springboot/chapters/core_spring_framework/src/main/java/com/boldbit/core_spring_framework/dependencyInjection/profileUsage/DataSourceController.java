package com.boldbit.core_spring_framework.dependencyInjection.profileUsage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/profile")
public class DataSourceController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/db")
    public String getMethodName() {
        return dataSource.connect();
    }
}
