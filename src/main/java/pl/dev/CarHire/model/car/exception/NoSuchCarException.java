package pl.dev.CarHire.model.car.exception;

import org.springframework.http.HttpStatus;

public class NoSuchCarException extends RuntimeException{
  public NoSuchCarException(String id) {
    super(HttpStatus.NOT_FOUND, "Car with id: " + id + " not found!");
  }
}
