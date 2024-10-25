package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository; // UserRepository 주입

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 암호화를 위한 BCryptPasswordEncoder 주입

    public void joinProcess(JoinDTO joinDTO) {
        // DB에 이미 동일한 username을 가진 회원이 존재하는지 확인
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return; // 이미 존재하는 경우, 가입 프로세스를 종료
        }

        UserEntity data = new UserEntity(); // 새로운 사용자 엔티티 생성

        // 사용자 정보 설정
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); // 비밀번호 암호화
        data.setRole("ROLE_ADMIN"); // 기본 역할 설정 (관리자)

        userRepository.save(data); // 사용자 정보를 DB에 저장
    }
}
