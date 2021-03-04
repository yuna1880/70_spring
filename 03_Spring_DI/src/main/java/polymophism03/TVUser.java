package polymophism03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		System.out.println("스프링 컨테이너 구동 전 !");
		//1. 스프링 컨테이너 구동 (스프링 (applicationContext.xml) 설정파일 읽어서)
		AbstractApplicationContext factory
				= new GenericXmlApplicationContext("applicationContext_03.xml");
		System.out.println("스프링 컨테이너 구동 후 !");
		
		//2.스프링 컨테이너 사용 : 생성된 객체를 요청.(lookup방식)
		System.out.println("----tv요청 사용-----");
		TV tv = (TV)factory.getBean("tv3Samsung2");
		tv.powerOn();
		tv.volumUp();
		tv.volumDown();
		tv.powerOff();
		System.out.println("tv : " + tv);
		
		
		System.out.println("스프링 컨테이너 구동 종료 처리 !");
		//3.스프링 컨테이너 종료(close)
		factory.close();
		
	}
}
