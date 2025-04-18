package com.example.chatbotai.domain.chats.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChatRequestDTO {

    @NotBlank(message = "질문을 입력해주세요.")
    private String question;

    private boolean isStreaming;

    private String model;
}
