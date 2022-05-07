package pl.dev.CarHire.exposure.spring.payload.request.update;

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
