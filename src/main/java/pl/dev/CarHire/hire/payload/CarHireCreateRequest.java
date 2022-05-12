package pl.dev.CarHire.hire.payload;

import lombok.Data;

@Data
public class CarHireCreateRequest {

  private Long userId;
  private Long carId;
  private String status;
  private int days;
  private float price;
}
