package pl.dev.CarHire.exposure.spring.payload.request.update;

import lombok.Data;

@Data
public class CityUpdateRequest {
  private Long id;
  private String name;
}
