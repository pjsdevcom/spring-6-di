package com.pjsdev.spring6di.services;

import org.springframework.stereotype.Service;

@Service("propertyGreetingService")
public class GreetingServicePropertyInjected implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello from property injected greeting service:(";
    }
}
