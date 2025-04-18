package com.example.chatbotai.domain.threads.controller;

import com.example.chatbotai.domain.threads.service.ThreadService;
import com.example.chatbotai.domain.threads.dto.ThreadResponseDTO;
import com.example.chatbotai.global.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ThreadController {

    private final ThreadService threadService;

    @GetMapping("/admin/threads")
    public ResponseEntity<List<ThreadResponseDTO>> getAllThreads(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "false") boolean asc
    ) {
        List<ThreadResponseDTO> responseDTO = threadService.getAllThreads(page, size, asc);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/threads")
    public ResponseEntity<List<ThreadResponseDTO>> getAllThreads(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "false") boolean asc
    ) {
        List<ThreadResponseDTO> responseDTO = threadService.getThreads(userDetails.getId(), page, size, asc);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
