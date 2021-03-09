package com.spring.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

//(실습) @Aspect, @Pointcut, @Around 적용 - allPointcut

@Service
@Aspect
public class AroundAdvice {

	//포인트컷 작성 : 명칭은 메소드명 사용 (대상 : 전체 다 적용)
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
		
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void getPointcut() {}
	
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(); //시간 재기 시작!
		
		System.out.println("[Aroud BEFORE] 비즈니스 로직 실행전 처리");
		Object returnObj = pjp.proceed(); //실행할 메소드 동작 시키기
		System.out.println("[Aroud AFTER] 비즈니스 로직 실행후 처리");
		
		stopWatch.stop(); //시간 정지
		
		System.out.println("[around]" + methodName + "()메소드, "
				+ "실행시간 : " + stopWatch.getTotalTimeMillis() + "초(ms)");
		
		return returnObj;
	}
	
	
}
