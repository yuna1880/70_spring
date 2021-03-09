package com.spring.biz.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service // 객체 (인스턴스) 생성
@Aspect //포인트컷 + advice 연결
public class BeforeAdvice {
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		//before가 실행되고 나서 실행될 메소드
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		System.out.println("args : " + Arrays.toString(args));
		
		//첫번째 파라미터값만 확인
		if (args == null || args.length < 1) {
			System.out.println("[사전처리]" + methodName + "() 메소드" + 
					", args 정보 :  없음  - 비즈니스 로직 수행전 로그");
		} else {
			System.out.println("[사전처리]" + methodName + "() 메소드" + 
					", args 정보 : " + args[0] + " - 비즈니스 로직 수행전 로그");			
		}
	}
}





