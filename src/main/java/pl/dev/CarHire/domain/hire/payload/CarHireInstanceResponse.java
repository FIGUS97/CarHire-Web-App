package pl.dev.CarHire.domain.hire.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarHireInstanceResponse {

  private String id;
  private UserInstanceResponse customer;
  private CarInstanceResponse car;
  private String status;
  private int days;
  private float price;
}
