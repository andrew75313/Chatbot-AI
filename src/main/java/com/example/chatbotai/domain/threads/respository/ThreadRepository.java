package com.example.chatbotai.domain.threads.respository;

import com.example.chatbotai.domain.threads.entity.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, UUID> {
    Optional<Thread> findTopByUserIdOrderByCreatedAtDesc(UUID id);

    Page<Thread> findAllByOrderByCreatedAtAsc(Pageable pageable);

    Page<Thread> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<Thread> findByIdOrderByCreatedAtAsc(Pageable pageable);

    Page<Thread> findByIdOrderByCreatedAtDesc(Pageable pageable);
}
