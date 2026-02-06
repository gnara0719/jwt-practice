package com.codeit.jwtpractice.util;

import com.codeit.jwtpractice.config.JwtProperties;
import com.codeit.jwtpractice.domain.user.User;
import com.codeit.jwtpractice.domain.user.UserRole;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtUilTest {

    @Autowired
    private JwtUil jwtUtil;
    @Autowired
    private JwtProperties jwtProperties;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .email("test@example.com")
                .password("encodedPassword")
                .name("테스트유저")
                .role(UserRole.USER)
                .build();
    }

    @Test
    @DisplayName("Access Token 생성 테스트")
    void generateAccessToken() {
        // when
        String token = jwtUtil.generateAccessToken(testUser);

        // then
        assertThat(token).isNotNull();
        assertThat(token).contains(".");
    }

    @Test
    @DisplayName("JWT 검증 및 Claims 추출 테스트")
    void validateToken() {
        // given
        String token = jwtUtil.generateAccessToken(testUser);

        // when
        Claims claims = jwtUtil.validateToken(token);

        // then
        assertThat(claims).isNotNull();
        assertThat(claims.getSubject()).isEqualTo(testUser.getEmail());
        assertThat(claims.getIssuer()).isEqualTo(jwtProperties.getIssuer());
        System.out.println("====== 생성된 JWT 토큰 ======");
        System.out.println(token);
        System.out.println("==========================");
    }
}