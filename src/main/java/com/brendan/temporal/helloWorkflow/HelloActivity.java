package com.brendan.temporal.helloWorkflow;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface HelloActivity {
    String greet(String greeting, String name);
}
