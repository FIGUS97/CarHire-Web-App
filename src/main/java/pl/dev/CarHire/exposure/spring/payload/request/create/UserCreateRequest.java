package pl.dev.CarHire.exposure.spring.payload.request.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequest {

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
