package pl.dev.CarHire.car.payload;

import lombok.Data;

@Data
public class CarUpdateRequest {
  private Long id;
  private String brand;
  private String model;
  private String status;
  private Long cityId;
  private float price;
}
