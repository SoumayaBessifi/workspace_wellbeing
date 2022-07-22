package com.esprit.workspace_wellbeing.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
	import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

	import com.fasterxml.jackson.databind.ObjectMapper;

	import lombok.extern.slf4j.Slf4j;
@Aspect
@Component
@Configuration
@Slf4j
public class LoggingAspect {
         private long t1, t2;

		@Pointcut("execution(* com.esprit.workspace_wellbeing.controller.CollaborationController.*(..))")
		public void loggingPointCut() {
			
		}
		
	
		@Before("loggingPointCut()")
	    public void before( JoinPoint joinPoint ){
	        log.info("Before method invoked::"+joinPoint.getSignature().getName());
	        System.out.println("------------------------------------------");
	       

	    }

       @After("loggingPointCut()")
        public void after( JoinPoint joinPoint ){
	    log.info("------------------------------------------");
        log.info("After method invoked::"+joinPoint.getSignature().getName());

       }

       
   	@Around("loggingPointCut()")
	public Object loggingAdvice(ProceedingJoinPoint pj) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pj.getSignature().getName();
		String className = pj.getTarget().getClass().toString();
		long start = System.currentTimeMillis();
		long elapsedTime = System.currentTimeMillis() - start;
		Object[] array = pj.getArgs();
	    log.info("******************************************");

		log.info("Inside "+className+ "class "+methodName+" method, with request : "+mapper.writeValueAsString(array));

		Object response = pj.proceed();
		log.info("Method execution time: " + elapsedTime + " milliseconds.");
	    log.info("******************************************");

		log.info("Inside "+className+ "class "+methodName+" method, with response : "+mapper.writeValueAsString(response));
		return response;
	}
	
       
	
}
