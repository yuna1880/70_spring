package com.spring.biz.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	//리턴타입 ModelAndView -> String
	//전달하는 데이터 타입 ModelAndView -> Model
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo , Model model, BoardDAO boardDAO) {
		System.out.println(">> 게시글 상세보기 - getBoard()");
		BoardVO board = boardDAO.getBoard(vo);
		model.addAttribute("board", board);

		return "getBoard.jsp";
	}
	
	@RequestMapping("/getBoardList.do")
	public String GetBoardList(BoardDAO boardDAO, Model model, BoardVO vo) {
		System.out.println(">>> 게시글 전체 목록 - String getBoardList()");
		//List<BoardVO> boardList = boardDAO.getBoardList();
		
		//검색조건 값이 없을때 기본값 설정
		if(vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		
		if(vo.getSearchKeyword() == null) {
			vo.setSearchCondition("");
		}
		
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		model.addAttribute("boardList", boardList); 
		
		return "getBoardList.jsp"; //뷰 명칭은 리턴타입 명칭으로 보낸다. modelandview 대신!
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 상세보기 - getBoard");
		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 수정처리 - updateBoard()");
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 삭제 - deleteBoard");
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}
}
