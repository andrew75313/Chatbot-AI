package com.example.chatbotai.domain.chats.entity;

import com.example.chatbotai.domain.threads.entity.Thread;
import com.example.chatbotai.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chats")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat extends Timestamped {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;
}
