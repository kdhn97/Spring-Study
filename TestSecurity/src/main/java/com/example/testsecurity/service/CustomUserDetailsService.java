package com.example.testsecurity.service;

import com.example.testsecurity.dto.CustomUserDetails;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 스프링의 서비스 계층임을 나타내는 어노테이션
@Service
// Spring Security의 UserDetailsService 인터페이스를 구현
// 사용자 인증에 필요한 정보를 로드하는 역할
public class CustomUserDetailsService implements UserDetailsService {

    // UserRepository 자동 주입
    // 사용자 정보를 데이터베이스에서 조회하는 데 사용
    @Autowired
    private UserRepository userRepository;

    // UserDetailsService 인터페이스의 메서드 구현
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자명으로 데이터베이스에서 사용자 정보 조회
        UserEntity userData = userRepository.findByUsername(username);

        // 사용자 정보가 존재하는 경우
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        // 사용자 정보가 없는 경우 null 반환
        return null;
    }
}