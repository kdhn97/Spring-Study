package com.example.testsecurity.dto;

import com.example.testsecurity.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Spring Security에서 사용자 정보를 담는 인터페이스인 UserDetails를 구현
public class CustomUserDetails implements UserDetails {
    // 실제 사용자 정보를 담고 있는 엔티티
    private UserEntity userEntity;

    // 생성자: UserEntity를 받아서 CustomUserDetails 객체 생성
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    // 사용자의 권한 목록을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 목록을 담을 ArrayList 생성
        Collection<GrantedAuthority> collection = new ArrayList<>();

        // 사용자의 권한을 GrantedAuthority 객체로 변환하여 추가
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }

    // 사용자의 비밀번호를 반환
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    // 사용자의 아이디를 반환
    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}