package com.leo.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringmvcExceptionHandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView exceptionHandler(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		System.out.println("---> 出异常了: "+ex);
		return mv;
	}
	
}
