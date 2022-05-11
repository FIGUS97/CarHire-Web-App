package pl.dev.CarHire.city.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityInstanceResponse {
  private Long id;
  private String name;
}
