package com.brendan.temporal.helloWorkflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorkflow  {
    @WorkflowMethod
    String sayHi(String name);
}
