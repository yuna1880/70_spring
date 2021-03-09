package com.spring.biz.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(">>> 게시글 상세보기");
		//1. 전달받은 데이터 추출(확인)
		String seq = request.getParameter("seq");
		
		//2. DB 연동 처리(글 하나 조회)
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
		//3. 검색결과를 세션에 저장(뷰에서 사용할 수 있게)
//		HttpSession session = request.getSession();
//		session.setAttribute("board", board);
		
		//4. ModelAndView에 데이터와 뷰이름 저장
		ModelAndView mav = new ModelAndView();
		//mav.addObject(board); //boardVO명칭으로 저장.
		mav.addObject("board", board);
		mav.setViewName("getBoard.jsp");
		return mav;
	}

}
