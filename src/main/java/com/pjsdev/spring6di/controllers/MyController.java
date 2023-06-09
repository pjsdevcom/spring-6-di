package com.pjsdev.spring6di.controllers;

import com.pjsdev.spring6di.services.GreetingService;
import com.pjsdev.spring6di.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final GreetingService greetingService;

    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }

    public String sayHello() {
        System.out.println("I'm in the controller!");

        return greetingService.sayGreeting();
    }

    public void beforeInit() {
        System.out.println("==> beforeInit() - called by bean post processor");
    }

    public void afterInit() {
        System.out.println("==> afterInit() - called by bean post processor");
    }
}
