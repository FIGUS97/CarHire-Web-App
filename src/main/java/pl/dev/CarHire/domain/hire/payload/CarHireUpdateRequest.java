package pl.dev.CarHire.domain.hire.payload;

import lombok.Data;

@Data
public class CarHireUpdateRequest {

  private String id;
  private String userId;
  private String carId;
  private String status;
  private int days;
  private float price;
}
