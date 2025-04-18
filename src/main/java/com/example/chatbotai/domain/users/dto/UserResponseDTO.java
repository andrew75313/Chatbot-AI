package com.example.chatbotai.domain.users.dto;

import com.example.chatbotai.domain.users.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public UserResponseDTO(User user) {
        this.id = user.getId().toString();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
