package pl.dev.CarHire.domain.car.payload;

import lombok.Data;

@Data
public class CarCreateRequest {

  private String brand;
  private String model;
  private String status;
  private String cityName;
  private float price;
}
