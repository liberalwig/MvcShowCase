package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

	@GetMapping("/exception")
	public String exception() {
		throw new IllegalStateException("Sorry!");
	}

	@GetMapping("/global-exception")
	public String businessException() throws BusinessException {

		throw new BusinessException();
	}

	@ExceptionHandler
	public String handle(IllegalStateException e) {
		System.out.println(e.getMessage());
		return "IllegalStateException handled!";
	}


	/*
	 * @ExceptionHandler public String handleBusinessException(BusinessException ex)
	 * { return "test IllegalStateException handled!"; }
	 */

}
