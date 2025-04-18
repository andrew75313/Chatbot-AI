package com.example.chatbotai.domain.threads.dto;

import com.example.chatbotai.domain.chats.dto.ChatResponseDTO;
import com.example.chatbotai.domain.threads.entity.Thread;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ThreadResponseDTO {
    private String id;
    private List<ChatResponseDTO> chats;
    private LocalDateTime createdAt;

    public ThreadResponseDTO(Thread thread) {
        this.id = thread.getId().toString();
        this.createdAt = thread.getCreatedAt();
        this.chats = thread.getChats().stream()
                .map(ChatResponseDTO::new)
                .collect(Collectors.toList());
    }
}
