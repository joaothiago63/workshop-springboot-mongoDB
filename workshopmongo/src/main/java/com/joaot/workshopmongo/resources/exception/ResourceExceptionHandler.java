package com.joaot.workshopmongo.resources.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaot.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
    	HttpStatus status = HttpStatus.NOT_FOUND;
    	StandardError error = new StandardError(new Date().getTime(),status.value(), "Não encontrado", e.getMessage(),request.getRequestURI());
    	return ResponseEntity.status(status).body(error);
    }

}
