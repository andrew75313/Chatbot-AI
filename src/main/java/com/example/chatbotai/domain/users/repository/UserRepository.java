package com.example.chatbotai.domain.users.repository;

import com.example.chatbotai.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<Object> findByUsername(String username);

    Optional<Object> findByEmail(String email);
}
