package pl.dev.CarHire.city.payload;

import lombok.Data;

@Data
public class CityUpdateRequest {
  private String id;
  private String name;
}
