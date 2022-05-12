package pl.dev.CarHire.hire.payload;

import lombok.Data;

@Data
public class CarHireUpdateRequest {

  private Long id;
  private Long userId;
  private Long carId;
  private String status;
  private int days;
  private float price;
}
