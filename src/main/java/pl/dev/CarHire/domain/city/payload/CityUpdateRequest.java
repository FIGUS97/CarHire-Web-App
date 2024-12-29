package pl.dev.CarHire.domain.city.payload;

import lombok.Data;

@Data
public class CityUpdateRequest {
  private String id;
  private String name;
}
