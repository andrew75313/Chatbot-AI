package com.example.chatbotai.domain.chats.controller;

import com.example.chatbotai.domain.chats.dto.ChatRequestDTO;
import com.example.chatbotai.domain.chats.dto.ChatResponseDTO;
import com.example.chatbotai.domain.chats.service.ChatService;
import com.example.chatbotai.global.security.user.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("")
    public ResponseEntity<ChatResponseDTO> createChat(@Valid @RequestBody ChatRequestDTO requestDTO,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ChatResponseDTO responseDTO = chatService.createChat(requestDTO, userDetails.getId());
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
