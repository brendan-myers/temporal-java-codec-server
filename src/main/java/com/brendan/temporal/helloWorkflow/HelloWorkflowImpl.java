package com.brendan.temporal.helloWorkflow;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;

import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

@WorkflowImpl(taskQueues = "hello-queue")
public class HelloWorkflowImpl implements HelloWorkflow {    
    private HelloActivity activity = Workflow.newActivityStub(
        HelloActivity.class,
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .build()
    );

    @Value("${greeting}")
    private String greeting;

    @Override
    public String sayHi(String name) {
        return activity.greet(greeting, name);
    }
}
