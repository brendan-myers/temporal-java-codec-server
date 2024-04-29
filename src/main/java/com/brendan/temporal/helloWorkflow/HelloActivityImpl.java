package com.brendan.temporal.helloWorkflow;

import org.springframework.stereotype.Component;

import io.temporal.spring.boot.ActivityImpl;

@Component
@ActivityImpl(taskQueues = "hello-queue")
public class HelloActivityImpl implements HelloActivity {

    @Override
    public String greet(String greeting, String name) {
        return greeting + ", " + name;
    }
    
}
