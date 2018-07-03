package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	
	@Before("execution(* get*())")
	public void LoggingAdvice(JoinPoint joinpoint){
		System.out.println(joinpoint.toString());
	}
	
	@Before("args()")
	public void secondAdvice(){
		System.out.println("Second advice executed");
	}
	
	@Pointcut("execution(* * get*())")
	public void allgetters(){
		
	}
		
}
