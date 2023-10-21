package com.example.exam2023.repository;

import com.example.exam2023.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String value);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
