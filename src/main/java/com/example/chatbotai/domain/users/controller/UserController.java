package com.example.chatbotai.domain.users.controller;

import com.example.chatbotai.domain.users.dto.SignupRequestDTO;
import com.example.chatbotai.domain.users.dto.UserResponseDTO;
import com.example.chatbotai.domain.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody SignupRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/admin/signup/{adminCode}")
    public ResponseEntity<UserResponseDTO> createAdmin(@PathVariable String adminCode,
                                                       @Valid @RequestBody SignupRequestDTO requestDTO) {
        UserResponseDTO responseDTO = userService.createAdmin(adminCode, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
