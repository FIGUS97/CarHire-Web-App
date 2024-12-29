package pl.dev.CarHire.domain.user.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInstanceResponse {

  private String id;
  private String name_surname;
}
