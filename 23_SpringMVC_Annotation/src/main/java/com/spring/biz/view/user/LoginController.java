package com.spring.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

//@Controller : DispatcherServlet에서 인식할 수 있는 컨트롤러로 객체 생성
@Controller 
public class LoginController {

	/*
	 * @RequestMapping : 요청과 처리 (Controller) 연결 (HandlerMapping 역할 대체)
	 * command 객체 사용 : 파라미터로 전달되는 값을 command 객체에서 설정.
	 * 스프링 프레임워크에서 객체 생성하고 파라미터 값을 설정해서 전달해준다.
	 * 1. UserVO 타입의 객체 생성
	 * 2. UserVO 타입의 객체에 전달받은 파라미터 값을 설정 (이름 일치하는 경우)
	 * 3. UserVO 타입의 객체를 메소드의 파라미터(인수)값으로 전달.
	 */
	@RequestMapping("/login.do")
	public String login(UserVO vo, UserDAO userDAO) {
		System.out.println(">> 로그인 처리 - login()");
		System.out.println(">전달받은 vo : " + vo);
		System.out.println(">전달받은 UserDao : " + userDAO);
		
		UserVO user = userDAO.getUser(vo);
		if(user != null) {
			return "getBoardList.do";
		} else {
			return "login.jsp ";
		}
		
	}
	
// 사용되지 않는 코드
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		
//		System.out.println(">>> 로그인 처리");
//		//1. 사용자 입력 데이터 확인(추출)
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		
//		//2. DB연동작업(id, password 유무 확인)
//		UserVO vo = new UserVO();
//		vo.setId(id);
//		vo.setPassword(password);
//		
//		UserDAO userDAO = new UserDAO();
//		UserVO user = userDAO.getUser(vo);
//		
//		//3. 화면 네비게이션(화면이동)
//		//로그인 성공 : 게시글 보여주기 - getBoardList.jsp
//		//로그인 실패 : 로그인화면으로 이동 - login.jsp 
//		ModelAndView mav = new ModelAndView();
//		if (user != null) { //사용자 정보가 있는 경우
//			System.out.println("> 로그인 성공!!!");
//			//response.sendRedirect("getBoardList.do");
//			//returnStr = "getBoardList.do";
//			mav.setViewName("redirect:getBoardList.do");
//		} else {
//			System.out.println("> 로그인 실패!!!");
//			//response.sendRedirect("login.do");
//			//returnStr = "login.do";
//			mav.setViewName("redirect:login.jsp"); //재요청 처리된다.
//		}
//		return mav;
//	}

}
