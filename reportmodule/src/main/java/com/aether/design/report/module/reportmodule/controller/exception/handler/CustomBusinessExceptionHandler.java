package com.aether.design.report.module.reportmodule.controller.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.aether.design.report.module.reportmodule.exception.model.ApiError;
import com.aether.design.report.module.reportmodule.exception.model.BusinessException;


/**
 * @author BAGDI Used for custom business exception
 *
 */
@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
//@Order(1)
public class CustomBusinessExceptionHandler {

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BusinessException> handleBusinessException(BusinessException ex, WebRequest request) {
		BusinessException businessException = new BusinessException(ex.getStatus(), ex.getMessage(), ex);
		return new ResponseEntity<BusinessException>(businessException, businessException.getStatus());
	}

	/**
	 * catch-all type of logic that deals with all other exceptions that don't have
	 * specific handlers
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleAll(Throwable ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
