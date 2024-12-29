package pl.dev.CarHire.domain.car.payload;

import lombok.Data;

@Data
public class CarUpdateRequest {
  private String id;
  private String brand;
  private String model;
  private String status;
  private String cityId;
  private float price;
}
