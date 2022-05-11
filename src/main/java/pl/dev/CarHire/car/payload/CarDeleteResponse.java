package pl.dev.CarHire.car.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDeleteResponse {

  private Long carId;
  private String message;
}
