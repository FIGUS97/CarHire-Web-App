package pl.dev.CarHire.model.hire.payload;

import lombok.Data;

@Data
public class CarHireCreateRequest {

  private String userId;
  private String carId;
  private String status;
  private int days;
  private float price;
}
