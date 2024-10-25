package com.example.testsecurity.repository;

import com.example.testsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받는 인터페이스
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username); // 주어진 username이 이미 존재하는지 확인하는 메서드
    UserEntity findByUsername(String username); // username으로 사용자 정보를 조회하는 메서드
}