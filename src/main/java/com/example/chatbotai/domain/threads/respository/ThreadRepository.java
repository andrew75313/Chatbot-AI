package com.example.chatbotai.domain.threads.respository;

import com.example.chatbotai.domain.threads.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, UUID> {
    Optional<Thread> findTopByUserIdOrderByCreatedAtDesc(UUID id);
}
