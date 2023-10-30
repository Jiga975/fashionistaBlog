package com.example.fashionista.repository;

import com.example.fashionista.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByMail(String mail);
    Optional<User>findUserEntitiesByMailAndPassword(String mail, String password);
}
