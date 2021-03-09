package com.spring.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	
	//포인트컷 작성 : 명칭은 메소드명 사용 (대상 : 전체 다 적용)
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	// JoinPoint 이외 매개변수 exceptObj 사용
	@AfterThrowing(pointcut = "allPointcut()", throwing = "exceptObj")
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();
		
		if (exceptObj instanceof IllegalArgumentException) {
			System.out.println(">> 부적합한 값이 전달되었습니다.");
		} else if (exceptObj instanceof NumberFormatException) {
			System.out.println(">> 숫자형식이 아닙니다.");
		} else if (exceptObj instanceof Exception) {
			System.out.println(">> 예외가 발생했습니다.");
		}
		
		//예외가 발생시 메세지 출력!
		System.out.println("[예외발생-throwing]" + methodName + "()메소드, "
				+ "- 실행중 예외발생, 메세지 : " + exceptObj.getMessage());
	}
}
