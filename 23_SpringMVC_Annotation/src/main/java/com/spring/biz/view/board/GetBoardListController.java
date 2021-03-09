package com.spring.biz.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class GetBoardListController {

	@RequestMapping("/getBoardList.do")
	public ModelAndView GetBoardList(BoardDAO boardDAO, ModelAndView mav) {
		System.out.println(">>> 게시글 전체 목록 - GetBoardList()");
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		mav.addObject("boardList", boardList); 
		mav.setViewName("getBoardList.jsp"); //뷰 리졸버 사용 안함
		
		return mav;
	}
	
}
