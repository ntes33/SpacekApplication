package it.digitazon.SpaceK.exception;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import it.digitazon.SpaceK.dto.Response;


@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(Exception.class)// exception type 
	 public ResponseEntity<Response> handlerAllException(Exception ex, WebRequest request){
		 Response errorResponse=Response.builder()
				 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				 .message(ex.getMessage())
				 .build();
		 
		 return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		 
		    
	  }
	 
	 @ExceptionHandler(NotFoundException.class)
	 public ResponseEntity<Response> handlerNotFoundException(NotFoundException ex, WebRequest request){
		 Response errorResponse=Response.builder()
				 .status(HttpStatus.NOT_FOUND.value())
				 .message(ex.getMessage())
				 .build();
		 
		 return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		 
		 
	  }
	 
	 
	 @ExceptionHandler(InvalidRequestException.class)
	 public ResponseEntity<Response> handlerInvalidRequestException(InvalidRequestException ex, WebRequest request){
		 Response errorResponse = Response.builder()
				 .status(HttpStatus.BAD_REQUEST.value())
				 .message(ex.getMessage())
				 .build();
		 
		 return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		 
		 
	  }
	
	
}
