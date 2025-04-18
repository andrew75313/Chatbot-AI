package com.example.chatbotai.domain.chats.service;

import com.example.chatbotai.domain.chats.dto.ChatRequestDTO;
import com.example.chatbotai.domain.chats.dto.ChatResponseDTO;
import com.example.chatbotai.domain.chats.entity.Chat;
import com.example.chatbotai.domain.chats.repository.ChatRepository;
import com.example.chatbotai.domain.threads.entity.Thread;
import com.example.chatbotai.domain.threads.respository.ThreadRepository;
import com.example.chatbotai.domain.users.entity.User;
import com.example.chatbotai.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final WebClient webClient;
    private final ThreadRepository threadRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    public ChatResponseDTO createChat(ChatRequestDTO request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );

        Thread thread = getOrCreateThread(user);

        String answer = requestOpenAI(request.getQuestion(), request.getModel());

        Chat chat = Chat.builder()
                .question(request.getQuestion())
                .answer(answer)
                .thread(thread)
                .build();

        chatRepository.save(chat);

        return new ChatResponseDTO(chat);
    }

    private Thread getOrCreateThread(User user) {
        Thread recentThread = threadRepository.findTopByUserIdOrderByCreatedAtDesc(user.getId()).orElse(null);

        if (recentThread == null || isOver30Min(recentThread.getCreatedAt())){

            Thread newThread = Thread.builder()
                    .user(user)
                    .build();

            return threadRepository.save(newThread);
        }

        return recentThread;
    }

    private boolean isOver30Min(LocalDateTime lastTime) {
        return Duration.between(lastTime, LocalDateTime.now()).toMinutes() >= 30;
    }

    private String requestOpenAI(String question, String model) {
        return webClient.post()
                .uri("/v1/chat/completions")
                .bodyValue(Map.of(
                        "model", model != null ? model : "gpt-3.5-turbo",
                        "messages", List.of(Map.of("role", "user", "content", question)),
                        "max_tokens", 500
                ))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
