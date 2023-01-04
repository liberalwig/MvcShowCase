package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public String handleBusinessException(BusinessException ex) {
		System.out.println(ex.getMessage());
		return "Handled BusinessException";
	}
}
