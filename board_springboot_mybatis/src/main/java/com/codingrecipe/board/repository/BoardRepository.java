package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 이 클래스가 데이터 접근 계층의 컴포넌트임을 나타냄
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class BoardRepository {
    private final SqlSessionTemplate sql; // MyBatis SQL 세션 템플릿 주입

    // 게시글 저장
    public BoardDTO save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
        return boardDTO;
    }

    // 모든 게시글 조회
    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    // 조회수 증가
    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    // ID로 특정 게시글 조회
    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    // 게시글 수정
    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    // 게시글 삭제
    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    // 첨부 파일 정보 저장
    public void saveFile(BoardFileDTO boardFileDTO) {
        sql.insert("Board.saveFile", boardFileDTO);
    }

    // 특정 게시글의 첨부 파일 조회
    public List<BoardFileDTO> findFile(Long id) {
        return sql.selectList("Board.findFile", id);
    }
}