package com.example.plannerapp.service;

import com.example.plannerapp.model.entity.Priority;
import com.example.plannerapp.model.enums.PriorityEnum;
import com.example.plannerapp.repository.PriorityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public void initPriority() {
        if (this.priorityRepository.count() != 0) {
            return;
        }

        Arrays.stream(PriorityEnum.values())
                .forEach(e -> {
                    Priority priority = new Priority();
                    priority.setPriorityName(e);
                    priority.setDescription(e.getValue());

                    this.priorityRepository.save(priority);
                });
    }

    public Priority findByPriorityNameEnum(PriorityEnum priority) {
        return priorityRepository.findByPriorityName(priority).orElse(null);
    }
}
