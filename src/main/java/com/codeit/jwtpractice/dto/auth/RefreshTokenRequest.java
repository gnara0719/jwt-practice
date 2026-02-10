package com.codeit.jwtpractice.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh Token은 필수")
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
