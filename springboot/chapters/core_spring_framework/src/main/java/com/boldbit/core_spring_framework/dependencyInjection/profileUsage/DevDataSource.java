package com.boldbit.core_spring_framework.dependencyInjection.profileUsage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDataSource implements DataSource{
    public String connect() {
        return "connecting to DevDataSource...";
    }
}
