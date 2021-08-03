package com.revature.registry.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggingAspect {

	final static Logger LOG = Logger.getLogger(LoggingAspect.class);

	@Before("execution(* com.revature.registry.*.*(..))")
	public void logBeforeService(JoinPoint jp) {
		String message = new String(jp.getTarget() + " called " + jp.getSignature());
		LOG.info(message);

	}

	@AfterThrowing(pointcut = "execution(* com.revature.registry.*.*(..))", throwing = "ex")
	public void logRegistryException(JoinPoint jp, Exception ex) {
		String message = new String(jp.getSignature() + " threw " + ex.getClass());
		LOG.info(message);

	}

	@AfterReturning(pointcut = "execution(* com.revature.registry.service.*.create*(..))", returning = "retVal")
	public void logServiceCreate(Object retVal) {
		try {
			String message = new String(retVal.getClass() + " was created.");
			LOG.info(message);
		} catch (Exception e) {

		}

	}

	@AfterReturning(pointcut = "execution(* com.revature.registry.service.*.get*(..))", returning = "retVal")
	public void logServiceGet(Object retVal) {
		try {
			String message = new String(retVal.getClass() + " was returned.");
			LOG.info(message);
		} catch (NullPointerException e) {
			System.out.println("Null was returned.");
		}

	}
}
