package com.codeit.jwtpractice.dto.user;

import com.codeit.jwtpractice.domain.user.User;
import com.codeit.jwtpractice.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String name,
        UserRole role,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt
) {
    public static UserResponse form(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getRole(), user.getCreatedAt());
    }
}
