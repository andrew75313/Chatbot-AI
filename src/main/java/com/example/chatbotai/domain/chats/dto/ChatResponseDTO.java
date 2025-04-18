package com.example.chatbotai.domain.chats.dto;

import com.example.chatbotai.domain.chats.entity.Chat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatResponseDTO {
    private String id;
    private String question;
    private String answer;
    private LocalDateTime createdAt;

    public ChatResponseDTO(Chat chat) {
        this.id = chat.getId().toString();
        this.question = chat.getQuestion();
        this.answer = chat.getAnswer();
        this.createdAt = chat.getCreatedAt();
    }
}
