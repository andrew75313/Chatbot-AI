package com.example.chatbotai.domain.chats.repository;

import com.example.chatbotai.domain.chats.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}
