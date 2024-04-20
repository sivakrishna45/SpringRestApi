package com.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exception.ResourceNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidErrors(MethodArgumentNotValidException ex){
		Map<String,String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
		errorMap.put(error.getField(),error.getDefaultMessage());
		});
		return errorMap;
	}
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String,String> handleResourceServiceException(ResourceNotFoundException ex){
		Map<String,String>  errorMap=new HashMap<>();
		errorMap.put("errorMessage",ex.getMessage());
		return errorMap;
	}
}
	
		
	
	
	


