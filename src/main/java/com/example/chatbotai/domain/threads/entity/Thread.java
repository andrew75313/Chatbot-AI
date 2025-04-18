package com.example.chatbotai.domain.threads.entity;

import com.example.chatbotai.domain.chats.entity.Chat;
import com.example.chatbotai.domain.users.entity.User;
import com.example.chatbotai.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "threads")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thread extends Timestamped {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();
}
