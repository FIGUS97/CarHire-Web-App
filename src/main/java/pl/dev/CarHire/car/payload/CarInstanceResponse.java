package pl.dev.CarHire.car.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarInstanceResponse {

  private String brand;
  private String model;
  private String status;
  private String cityName;
  private float price;
}
