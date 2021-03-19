package com.spring.biz.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	@Autowired //의존주입(DI) : 동일한 데이터 타입 객체
	private BoardService boardService; //의존주입<-- BoardServiceImpl
	
	public BoardController() {
		System.out.println("---->>> BoardController() 객체생성");
		System.out.println("> boardService : " + boardService); //null
	}

	//메소드에 선언된 @ModelAttribute : 리턴된 데이터를 View에 전달
	//@ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행됨
	//뷰에 전달될 때 설정된 명칭(예: conditionMap)으로 전달
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("--->>@ModelAttribute - Map searchConditionMap()");
		//key : 제목, value : TITLE
		//key : 내용, value : CONTENT
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	
	//리턴타입 ModelAndView -> String
	//전달하는 데이터 타입 ModelAndView -> Model
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println(">>> 글상세 - String getBoard()");
		BoardVO board = boardService.getBoard(vo);
		model.addAttribute("board", board);
		
		return "getBoard.jsp";
	}	
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 전체 목록- String getBoardList()");
		System.out.println("> boardService : " + boardService);
		
		//검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		model.addAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}	
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(">>> 게시글 입력 - insertBoard()");
		System.out.println("vo : " + vo);
		
		/* 파일업로드 관련 처리
		 * MultipartFile 인터페이스 주요 메소드
		 * String getOriginalFilename() : 업로드할 파일명 찾기
		 * void transferTo(File) : 업로드할 파일을 업로드 처리
		 * boolean isEmpty() : 업로드할 파일의 존재여부(없으면 true)
		 */
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) { //파일이 있으면
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/MyStudy/temp/" + fileName));
		}
		
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}	
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo) {
		System.out.println(">>> 글수정 - updateBoard()");
		boardService.updateBoard(vo);
		
		return "getBoardList.do";
	}	
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		System.out.println(">>> 글수정 - deleteBoard()");
		boardService.deleteBoard(vo);
		
		return "getBoardList.do";
	}	
	
	//Ajax 요청을 받고 JSON 배열 데이터 리턴
	@RequestMapping("/ajaxGetBoardList.do")
	@ResponseBody
	public List<BoardVO> ajaxGetBoardList(BoardVO vo) {
		List<BoardVO> boardList = boardService.getBoardList(vo);
		System.out.println("boardList : " + boardList);
		
		return boardList;
	}
	
}








