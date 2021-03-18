package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;

@Controller
public class UserController {
	//(실습)UserService 타입 주입받아서 사용 형태로 변경
	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("---->>> UserController() 생성");
	}

	//@RequestMapping("/login.do") //GET, POST 모두 처리
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo) {
		System.out.println(">>> 로그인처리 - login()");
		
		//일부러 예외 발생(Exception 처리 테스트용)
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력하세요");
		}		
		
		UserVO user = userService.getUser(vo);
		if (user != null) {
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
	}
	
	/* @ModelAttribute : 모델(Model) 속성값으로 저장(속성명 별도 지정)
	 * 	별도 지정 명칭 부여 안하면 <데이터 타입>의 첫글자 소문자로 작성된 명칭 사용
	 * @ModelAttribute UserVO --> 속성명 userVO 형태
	 * @ModelAttribute("user") UserVO ---> 속성명 user 사용
	 * Command 객체가 기본 request 스코프에 저장 사용됨
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	//public String loginView(UserVO vo) { //뷰에서 사용할 명칭 userVO 데이터 저장
	public String loginView(@ModelAttribute("user") UserVO vo) { //Model 속성명 user 사용
		System.out.println(">>> 로그인 페이지로 이동 - loginView()");
		vo.setId("test");
		vo.setPassword("test");
		
		return "login.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println(">>> 로그아웃 - logout()");
		//1.세션 초기화(세션 객체 종료)
		session.invalidate();

		return "login.jsp";
	}	
}
