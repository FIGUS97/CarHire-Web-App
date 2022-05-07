package pl.dev.CarHire.exposure.spring.payload.request.update;

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
