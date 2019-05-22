package com.meteor.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvise {

	@ExceptionHandler(Throwable.class)
	public ModelAndView throwables(Throwable exception) {
		ModelAndView modelAndView = new ModelAndView();
		exception.printStackTrace();
		modelAndView.setViewName("dutch/dutchList");
		return modelAndView;
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView constraintViolationException(ConstraintViolationException exception) {
		ModelAndView modelAndView = new ModelAndView();
		exception.printStackTrace();
		modelAndView.setViewName("dutch/dutchList");
		return modelAndView;
	}

}
