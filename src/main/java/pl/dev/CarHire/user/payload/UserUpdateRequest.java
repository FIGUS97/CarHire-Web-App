package pl.dev.CarHire.user.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
