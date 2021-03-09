package com.spring.biz.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

//@WebServlet("*.do") //web.xml 설정방식으로 변경.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	@Override
	public void init() throws ServletException {
		super.init();
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./"); //위치경로
		viewResolver.setSuffix(".jsp"); //파일타입
		
	}
	
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 처리 작업 진행 
		System.out.println(">> DispatcherServlet.process() : *.do 요청에 대한 처리를 진행.");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//요청 처리 작업 진행
		System.out.println(">> DispatcherServlet.process() : *.do 요청에 대한 처리를 진행.");
		
		//1. 클라이언트에서 어떤 작업을 요청했는지 확인하기
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		//마지막 슬래시에서 부터 자르기.
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path : " + path);
		
		//2. 클라이언트의 요청형태에 따른 작업처리
		Controller ctrl = handlerMapping.getController(path);
		System.out.println(">>ctrl = " + ctrl);
		
		//3. 검색된 컨트롤러 실행(컨트롤러 handleRequest 메소드 호출)
		String viewName = ctrl.handleRequest(request, response);
		System.out.println(">>viewName = " + viewName);
		
		//4. 이동할 view 이름 확인하고 응답페이지 작성 
		String view = null; //최종 요청처리할 URL 값
		if (viewName.contains(".do")) {
			view = viewName;
		} else {
			//.do가 없을시, viewResolver를 타고 접두어, 접미어가 붙는다.
			view = viewResolver.getView(viewName);
		}
		
		//5. 재요청 처리
		response.sendRedirect(view);
		
		
		
		
		
		/*
		if (path.equals("/login.do")) {
			System.out.println(">>> 로그인 처리");
			//1. 사용자 입력 데이터 확인(추출)
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//2. DB연동작업(id, password 유무 확인)
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			
			//3. 화면 네비게이션(화면이동)
			//로그인 성공 : 게시글 보여주기 - getBoardList.jsp
			//로그인 실패 : 로그인화면으로 이동 - login.jsp 
			if (user != null) { //사용자 정보가 있는 경우
				System.out.println("> 로그인 성공!!!");
				response.sendRedirect("getBoardList.do");
			} else {
				System.out.println("> 로그인 실패!!!");
				response.sendRedirect("login.do");
			}
			
		} else if (path.equals("/logout.do")) {
			System.out.println(">>> 로그아웃 처리");
			//1. 세션 초기화 (세션 객체 종료)
			request.getSession().invalidate();

			//2. 화면 네비게이션 (로그인페이지)
			response.sendRedirect("login.jsp");
		} else if (path.equals("/getBoardList.do")) {
			System.out.println(">>> 게시글 전체 목록 보여주기 처리");
			//1. 게시글 목록 조회(SELECT)
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList();
			
			//2. 검색결과를 세션에 저장 (getBoardList.jsp에서 쓸 수 있도록)
			request.getSession().setAttribute("boardList", boardList);
			
			//3. 화면 이동
			response.sendRedirect("getBoardList.jsp");
			
			
		} else if (path.equals("/getBoard.do")) {
			System.out.println(">>> 게시글 상세보기");
			//1. 전달받은 데이터 추출(확인)
			String seq = request.getParameter("seq");
			
			//2. DB 연동 처리(글 하나 조회)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			//3. 검색결과를 세션에 저장(뷰에서 사용할 수 있게)
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			
			//4. 상세보기 화면으로 이동
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.equals("/insertBoard.do")) {
			System.out.println(">>> 게시글 입력처리");
			//1. 전달받은 데이터 추출(확인)
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. DB연동 처리 - 데이터 입력
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			
			//3. 화면 네비게이션(목록페이지로 이동)
			response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/updateBoard.do")) {
			System.out.println(">>> 게시글 수정처리");
			
			//1. 전달받은 파라미터 값 추출(확인)
			request.setCharacterEncoding("UTF-8");
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. 업무처리 - DB 연동작업(게시글 수정)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			
			//3. 화면 네비게이션(목록 페이지로) : 데이터 다시 읽어오기
			response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/deleteBoard.do")) {
			System.out.println(">>> 게시글 삭제처리");
			//1. 전달받은 파라미터 값 추출(확인)
			request.setCharacterEncoding("UTF-8");
			String seq = request.getParameter("seq");
			
			//2. 업무처리 - DB 연동작업(게시글 삭제)
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);
			
			//3. 화면 네비게이션(목록 페이지로) : 데이터 다시 읽어오기
			response.sendRedirect("getBoardList.do");
		} */
	} 
}
