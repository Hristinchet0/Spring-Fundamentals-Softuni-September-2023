package com.example.plannerapp.repository;

import com.example.plannerapp.model.entity.Priority;
import com.example.plannerapp.model.enums.PriorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    Optional<Priority> findByPriorityName(PriorityEnum priority);
}
