package pl.dev.CarHire.model.city.payload;

import lombok.Data;

@Data
public class CityUpdateRequest {
  private String id;
  private String name;
}
