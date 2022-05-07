package pl.dev.CarHire.city.payload;

import lombok.Data;

@Data
public class CityUpdateRequest {
  private Long id;
  private String name;
}
