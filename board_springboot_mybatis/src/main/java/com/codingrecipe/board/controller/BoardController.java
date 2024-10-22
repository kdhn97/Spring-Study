package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller // 이 클래스가 Spring MVC 컨트롤러임을 나타냄
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class BoardController {
    private final BoardService boardService; // BoardService 의존성 주입

    @GetMapping("/save")
    public String save() {
        return "save"; // save.html 뷰를 반환
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO); // 게시글 저장
        return "redirect:/list"; // 저장 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll(); // 모든 게시글 조회
        model.addAttribute("boardList", boardDTOList); // 모델에 게시글 목록 추가
        System.out.println("boardDTOList = " + boardDTOList);
        return "list"; // list.html 뷰를 반환
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        boardService.updateHits(id); // 조회수 증가
        BoardDTO boardDTO = boardService.findById(id); // 특정 ID의 게시글 조회
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        if (boardDTO.getFileAttached() == 1) {
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id); // 첨부 파일 조회
            model.addAttribute("boardFileList", boardFileDTOList);
        }
        return "detail"; // detail.html 뷰를 반환
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id); // 수정할 게시글 조회
        model.addAttribute("board", boardDTO);
        return "update"; // update.html 뷰를 반환
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO); // 게시글 업데이트
        BoardDTO dto = boardService.findById(boardDTO.getId()); // 업데이트된 게시글 재조회
        model.addAttribute("board", dto);
        return "detail"; // detail.html 뷰를 반환
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id); // 게시글 삭제
        return "redirect:/list"; // 삭제 후 목록 페이지로 리다이렉트
    }
}