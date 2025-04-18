package com.example.chatbotai.domain.threads.service;

import com.example.chatbotai.domain.chats.repository.ChatRepository;
import com.example.chatbotai.domain.threads.dto.ThreadResponseDTO;
import com.example.chatbotai.domain.threads.entity.Thread;
import com.example.chatbotai.domain.threads.respository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ChatRepository chatRepository;

    public List<ThreadResponseDTO> getAllThreads(int page, int size, boolean asc) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);

        Page<Thread> threadPages;
        if (asc) {
            threadPages = threadRepository.findAllByOrderByCreatedAtAsc(pageable);
        } else {
            threadPages = threadRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        return threadPages.stream()
                .map(ThreadResponseDTO::new)
                .toList();
    }

    public List<ThreadResponseDTO> getThreads(UUID id, int page, int size, boolean asc) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);

        Page<Thread> threadPages;
        if (asc) {
            threadPages = threadRepository.findByIdOrderByCreatedAtAsc(pageable);
        } else {
            threadPages = threadRepository.findByIdOrderByCreatedAtDesc(pageable);
        }

        return threadPages.stream()
                .map(ThreadResponseDTO::new)
                .toList();
    }
}
