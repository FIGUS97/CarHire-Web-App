package pl.dev.CarHire.domain.city.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInstanceResponse {
  private String id;
  private String name;
}
