package pl.dev.CarHire.domain.common;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

  private HttpStatus status;
  private String path;
  private List<String> errors;

  public ApiError(HttpStatus status, String path, List<String> errorMessages) {
    super();
    this.status = status;
    this.path = path;
    this.errors = errorMessages;
  }

  public ApiError(HttpStatus status, String path, String errorMessage) {
    super();
    this.status = status;
    this.path = path;
    errors = Arrays.asList(errorMessage);
  }
}
