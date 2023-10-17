package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.enums.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Optional<Condition> findByConditionName(ConditionEnum condition);
}
