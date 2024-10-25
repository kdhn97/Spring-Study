package com.example.testsecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// JPA 엔티티임을 나타내는 어노테이션
@Entity
@Setter
@Getter
public class UserEntity {

    // 기본키(Primary Key) 지정
    @Id
    // 기본키 생성 전략을 DB의 자동 증가(AUTO_INCREMENT)로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) // username 필드를 유니크 제약조건으로 설정
    private String username;
    private String password;
    private String role;
}