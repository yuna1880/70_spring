package com.spring.biz.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board") //board 라는 이름의 Model이 있으면 Session에 저장!
public class BoardController {
	
	//메소드에 선언된 @ModelAttribute : 리턴된 데이터를 View에 전달 (공통적으로 사용)
	@ModelAttribute("conditionMap")
	//@ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행됨.
	//뷰에 전달될 때 설정된 명칭 (예: conditionMap)으로 전달
	public Map<String, String> searchConditionMap() {
		System.out.println("--->>@ModelAttribute - (Map) searchConditionMap() 실행");
		//key : 제목, value : TITLE
		//key : 내용, value : CONTENT
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		conditionMap.put("낭낭", "CONTENT1");
		return conditionMap;
	}
	
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
	//인수(파라미터)에 설정한 @ModelAttribute("board") : 속성명 board가 있으면 적용
	//없으면 새로 만들어서 적용
	//기존 파라미터는 그대로 적용, 바뀐 값은 파라미터 적용되어서 업데이트
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 수정처리 - updateBoard()");
		System.out.println(">>> board : " + vo);
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
