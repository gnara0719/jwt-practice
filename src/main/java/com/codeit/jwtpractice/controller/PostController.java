package com.codeit.jwtpractice.controller;

import com.codeit.jwtpractice.domain.post.Post;
import com.codeit.jwtpractice.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    // 게시글 작성
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createPost(@RequestBody Post post, @AuthenticationPrincipal UserPrincipal principal) {
        return ResponseEntity.ok(post);
    }

    // 게시글 수정, 삭제 => ADMIN은 모두 가능, 일반 USER는 자신의 글만 수정 or 삭제
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or ")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
