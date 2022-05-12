package pl.dev.CarHire.user.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInstanceResponse {

  private String name_surname;
}
