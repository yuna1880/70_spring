package com.spring.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1.스프링 컨테이너 구동
		GenericXmlApplicationContext container
			= new GenericXmlApplicationContext("applicationContext.xml");
		
		//2.스프링 컨테이너 사용
		System.out.println("==========컨테이너 구동 후============");
		UserService userService = (UserService)container.getBean("userService");
		
		
		//데이터 
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassward("test");
		
		UserVO vo2 = userService.getUser(vo);
		
		if(vo2 == null) {
			System.out.println("잘못된 User 정보입니다.");
		} else {
			System.out.println(vo2);			
		}
		
		//3. 컨테이너 종료
		
		container.close();
		
	}

}
