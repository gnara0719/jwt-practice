package com.codeit.jwtpractice.security;

import com.codeit.jwtpractice.domain.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String email;
    private final String name;
    private final Collection<? extends GrantedAuthority> authorities;

    // jwt Claims로부터 UserPrincipal 생성
    // 정적 팩토리 메서드 이름을 지을 때
    // from: 하나의 매개변수를 받아서 특정 타입으로 리턴
    // of: 여러개의 매개변수를 받아서 적절한 개체를 생성
    public static UserPrincipal of(Long id, String email, String name, String role) {
        Collection<GrantedAuthority> authorities
                = Collections.singletonList(new SimpleGrantedAuthority("Role_" + role));

        return new UserPrincipal(id, email, name, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // jwt 방식에서는 비밀번호 사용 x
    }

    // 계정명
    @Override
    public String getUsername() {
        return email;
    }
}
