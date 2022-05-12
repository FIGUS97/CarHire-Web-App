package pl.dev.CarHire.hire.payload;

import lombok.Builder;
import lombok.Data;
import pl.dev.CarHire.car.payload.CarInstanceResponse;
import pl.dev.CarHire.user.payload.UserInstanceResponse;

@Data
@Builder
public class CarHireInstanceResponse {

  private Long id;
  private UserInstanceResponse user;
  private CarInstanceResponse car;
  private String status;
  private int days;
  private float price;
}
