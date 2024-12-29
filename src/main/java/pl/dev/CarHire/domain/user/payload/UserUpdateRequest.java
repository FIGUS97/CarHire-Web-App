package pl.dev.CarHire.domain.user.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

  private String id;
  private String roleName;
  private String cityName;
  private String responsibility;
  private String name;
  private String surname;
  private String age;
  private String status;
  private String email;
  private String username;
  private String password;

}
