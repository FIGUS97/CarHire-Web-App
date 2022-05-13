package pl.dev.CarHire.car.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInstanceResponse {

  private String brand;
  private String model;
  private String status;
  private String cityName;
  private float pricePerDay;
}
