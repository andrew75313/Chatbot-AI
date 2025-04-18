package com.example.chatbotai.domain.users.service;

import com.example.chatbotai.domain.users.dto.SignupRequestDTO;
import com.example.chatbotai.domain.users.dto.UserResponseDTO;
import com.example.chatbotai.domain.users.entity.User;
import com.example.chatbotai.domain.users.entity.UserRole;
import com.example.chatbotai.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${admin.signup.code}")
    private String adminCode;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(SignupRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = passwordEncoder.encode(requestDTO.getPassword());
        String email = requestDTO.getEmail();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(UserRole.MEMBER)
                .build();

        userRepository.save(user);

        return new UserResponseDTO(user);
    }

    public UserResponseDTO createAdmin(String requestAdminCode, SignupRequestDTO requestDTO) {
        if (!requestAdminCode.equals(adminCode)) {
            throw new IllegalArgumentException("관리자 코드를 다시 확인해주세요.");
        }

        String username = requestDTO.getUsername();
        String password = passwordEncoder.encode(requestDTO.getPassword());
        String email = requestDTO.getEmail();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(UserRole.ADMIN)
                .build();

        userRepository.save(user);

        return new UserResponseDTO(user);
    }
}
