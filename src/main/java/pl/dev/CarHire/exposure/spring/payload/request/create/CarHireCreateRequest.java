package pl.dev.CarHire.exposure.spring.payload.request.create;

import lombok.Data;

@Data
public class CarHireCreateRequest {

  private Long userId;

  private Long carId;

  private String status;

  private int days;

  private float price;
}
