package com.example.testsecurity.controller;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

// 스프링 MVC 컨트롤러임을 나타내는 어노테이션
@Controller
public class MainController {

    // 메인 페이지 요청 처리 ("/" 경로)
    @GetMapping("/")
    public String mainP(Model model) {
        // SecurityContext에서 현재 인증된 사용자의 이름(ID) 가져오기
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        // 현재 인증된 사용자의 인증 정보 전체를 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자의 권한 정보 컬렉션 가져오기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 권한 정보를 순회하기 위한 Iterator 생성
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        // 첫 번째 권한 정보 가져오기
        GrantedAuthority auth = iter.next();
        // 권한 문자열 추출
        String role = auth.getAuthority();

        model.addAttribute("id", id); // 모델에 사용자 ID 추가
        model.addAttribute("role", role); // 모델에 사용자 권한 추가

        return "main";
    }
}