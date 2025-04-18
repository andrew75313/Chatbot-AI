package com.example.chatbotai.domain.users.entity;

public enum UserRole {
    MEMBER("MEMBER"),
    ADMIN("ADMIN");

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString(){
        return authority;
    }
}

