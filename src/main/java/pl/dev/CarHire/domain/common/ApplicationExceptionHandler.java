package pl.dev.CarHire.domain.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.dev.CarHire.domain.car.exception.NoSuchCarException;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.user.exception.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  public ApplicationExceptionHandler() {
  }

  @ExceptionHandler(NoSuchCityException.class)
  public ResponseEntity<Object> handleNoCityException(NoSuchCityException exception, WebRequest request) {
    return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(NoSuchCarException.class)
  public ResponseEntity<Object> handleNoCarException(NoSuchCarException exception, WebRequest request) {
    return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
  }
  
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
    return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
  }


  private ResponseEntity<Object> buildErrorResponse (Exception ex, HttpStatus status, WebRequest request) {
    ApiError errorResponse = new ApiError(status, getRequestURI(request), ex.getMessage());
    ex.printStackTrace();
    return ResponseEntity.status(status).body(errorResponse);
  }

  String getRequestURI(WebRequest request) {
    return (request instanceof ServletWebRequest) ? ((ServletWebRequest) request).getRequest().getRequestURI() : null;
  }
}
