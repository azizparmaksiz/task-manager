package com.task.controller;

import com.task.exception.CommonExceptionMessage;
import com.task.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.transaction.RollbackException;
import java.sql.SQLException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
private static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = {ResourceNotFoundException.class })
	@ResponseBody
	protected ResponseEntity<CommonExceptionMessage> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		logger.error(ex.getMessage());
		return new ResponseEntity<>(ex.getCommonExceptionMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { SQLException.class, RollbackException.class })
	@ResponseBody
	protected ResponseEntity<CommonExceptionMessage> handleSQLException(RuntimeException ex, WebRequest request) {
		logger.error(ex.getMessage());
			return new ResponseEntity<>(new CommonExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
