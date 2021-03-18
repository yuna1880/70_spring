package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

@Controller
public class UserController {
	/*
	 * @RequestMapping : 요청과 처리 (Controller) 연결 (HandlerMapping 역할 대체)
	 * command 객체 사용 : 파라미터로 전달되는 값을 command 객체에서 설정.
	 * 스프링 프레임워크에서 객체 생성하고 파라미터 값을 설정해서 전달해준다.
	 * 1. UserVO 타입의 객체 생성
	 * 2. UserVO 타입의 객체에 전달받은 파라미터 값을 설정 (이름 일치하는 경우)
	 * 3. UserVO 타입의 객체를 메소드의 파라미터(인수)값으로 전달.
	 */
	
	//@RequestMapping("/login.do") //GET, POST 모두 처리
	@RequestMapping(value = "/login.do", method = RequestMethod.POST) //post만 처리
	//@PostMapping("/login.do")
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
	
	/*
	 * @ModelAttribute : 모델 (Model) 속성값으로 저장(속성명 별도 지정)
	 * 별도 지정 명칭 부여 안하면 <데이터타입>의 첫글자 소문자로 작성된 명칭
	 * @ModelAttribute UserVO -> 속성명 UserVO 형태
	 * @ModelAttribute("user") UserVO -> 속성명 user 사용
	 * Command 객체가 기본 request 스코프에 저장된다.
	 */
	
	
	// GET -> 으로 넘어왔을시, 아래 코드 실행. (index.jsp에서 넘어오는 요청은 GET)
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	//@GetMapping("/login.do")
	//public String loginView(UserVO vo) { //뷰에서 사용할 명칭 userVO 명칭으로 데이터 저장된다.
	public String loginView(@ModelAttribute("user") UserVO vo) { //Model 속성 명칭 지정!!  user 명칭으로 데이터 저장된다.
		System.out.println(">> 로그인 페이지 이동 - loginView()");
		vo.setId("test");
		vo.setPassword("test");
		
		return "login.jsp";
	}
		
	
		@RequestMapping("/logout.do")
		public String logout(HttpSession session) {
			System.out.println(">>> 로그아웃 처리");
			//1. 세션 초기화 (세션 객체 종료)
			session.invalidate();
			//2. 화면 네비게이션 (로그인페이지)
			return "login.jsp";
		}
	}

	