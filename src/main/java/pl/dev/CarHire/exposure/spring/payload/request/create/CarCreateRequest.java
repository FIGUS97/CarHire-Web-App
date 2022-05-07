package pl.dev.CarHire.exposure.spring.payload.request.create;

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
