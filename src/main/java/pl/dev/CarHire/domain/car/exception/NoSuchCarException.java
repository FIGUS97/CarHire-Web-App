package pl.dev.CarHire.domain.car.exception;

public class NoSuchCarException extends RuntimeException{
  public NoSuchCarException(String id) {
      super("Car with id: " + id + " not found!");
  }
}
