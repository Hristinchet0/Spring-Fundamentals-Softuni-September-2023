package com.example.resellerapp.service;

import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.enums.ConditionEnum;
import com.example.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initCondition() {
        if (this.conditionRepository.count() != 0) {
            return;
        }

        Arrays.stream(ConditionEnum.values())
                .forEach(e -> {
                    Condition condition = new Condition();
                    condition.setConditionName(e);
                    condition.setDescription(e.getValue());

                    this.conditionRepository.save(condition);
                });
    }

    public Condition findByConditionNameEnum(ConditionEnum condition) {
        return conditionRepository.findByConditionName(condition).orElse(null);
    }
}
