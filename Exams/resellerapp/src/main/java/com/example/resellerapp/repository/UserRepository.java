package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.User;
import org.modelmapper.TypeMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String value);
}
