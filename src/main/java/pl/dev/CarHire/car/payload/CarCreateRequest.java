package pl.dev.CarHire.car.payload;

import lombok.Builder;
import lombok.Data;

@Data
public class CarCreateRequest {

/*
  - TODO: City ID to City name
*/

  private String brand;
  private String model;
  private String status;
  private Long cityId;
  private float price;
}
