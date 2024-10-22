package com.codingrecipe.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 해당 클래스가 하나 이상의 @Bean 메서드를 포함하고 있음을 선언
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // 뷰에서 접근할 경로 패턴
    private String savePath = "file:///Users/codingrecipe/development/intellij_community/spring_upload_files/"; // 실제 파일이 저장될 서버의 로컬 경로

    @Override // 메서드 오버라이딩을 명시적으로 선언
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)  // 웹 요청 URL 패턴 설정
                .addResourceLocations(savePath);   // 실제 리소스가 존재하는 외부 경로 설정
    }
}
