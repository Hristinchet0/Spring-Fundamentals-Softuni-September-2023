package com.example.plannerapp.init;

import com.example.plannerapp.service.PriorityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PriorityInit implements CommandLineRunner {

    private final PriorityService priorityService;

    public PriorityInit(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public void run(String... args) throws Exception {
        priorityService.initPriority();
    }
}
