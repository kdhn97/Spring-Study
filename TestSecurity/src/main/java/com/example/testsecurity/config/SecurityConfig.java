package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 스프링 설정 클래스임을 나타내는 어노테이션
@EnableWebSecurity // 스프링 시큐리티 설정을 활성화하는 어노테이션
public class SecurityConfig {

    // 비밀번호 암호화를 위한 BCrypt 인코더를 빈으로 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 스프링 시큐리티 필터 체인 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "join", "/joinProc").permitAll() // 누구나 접근 가능한 URL 설정
                        .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 역할만 접근 가능한 URL 설정
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // ADMIN 또는 USER 역할이 있어야 접근 가능한 URL 설정
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근 가능
                );
        http
                .formLogin((auth) -> auth
                        .loginPage("/login") // 커스텀 로그인 페이지 URL 설정
                        .loginProcessingUrl("/loginProc") // 로그인 처리 URL 설정
                        .permitAll() // 로그인 페이지는 누구나 접근 가능
                );
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) // 최대 세션 수를 1로 제한
                        .maxSessionsPreventsLogin(true)); // 동시 로그인 차단

        return http.build();
    }
}