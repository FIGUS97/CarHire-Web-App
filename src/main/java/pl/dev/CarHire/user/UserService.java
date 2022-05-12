package pl.dev.CarHire.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dev.CarHire.city.City;
import pl.dev.CarHire.common.payload.DeleteResponse;
import pl.dev.CarHire.role.Role;
import pl.dev.CarHire.city.CityRepository;
import pl.dev.CarHire.role.RoleRepository;
import pl.dev.CarHire.user.payload.UserCreateRequest;
import pl.dev.CarHire.user.payload.UserInstanceResponse;
import pl.dev.CarHire.user.payload.UserUpdateRequest;

@Service
public class UserService {

        /*
        - TODO: Obsługa błędów http
        - TODO: Mappery
     */

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private RoleRepository roleRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).get();
  }

  public List<UserInstanceResponse> findBy(String role, String city, String nameSurname, String status, String email, String username) {
    List<UserInstanceResponse> responses = new ArrayList<>();

    List<User> users = userRepository.findByAttributes(roleRepository.findByName(role), cityRepository.findByName(city), nameSurname, status, email, username);

    users.forEach(user -> responses.add(userToUserInstanceResponse(user)));

    return responses;
  }

  public UserInstanceResponse addUser(UserCreateRequest newUser) throws HttpResponseException {
    City city = cityRepository.findByName(newUser.getCityName());
    Role role = roleRepository.findByName(newUser.getRoleName());

    User user = User.builder()
        .role(role)
        .city(city)
        .responsibility(newUser.getResponsibility())
        .name_surname(newUser.getName() + " " + newUser.getSurname())
        .age(newUser.getAge())
        .status(newUser.getStatus())
        .email(newUser.getEmail())
        .username(newUser.getUsername())
        .password(newUser.getPassword())
        .build();

    User savedUser = userRepository.save(user);
    city.getUsers().add(savedUser);

    return userToUserInstanceResponse(savedUser);
  }

  public UserInstanceResponse updateUser(UserUpdateRequest providedUser) {

    City city = cityRepository.findByName(providedUser.getCityName());
    Role role = roleRepository.findByName(providedUser.getRoleName());

    User user = User.builder()
        .id(providedUser.getId())
        .role(role)
        .city(city)
        .responsibility(providedUser.getResponsibility())
        .name_surname(providedUser.getName() + " " + providedUser.getSurname())
        .age(providedUser.getAge())
        .status(providedUser.getStatus())
        .email(providedUser.getEmail())
        .username(providedUser.getUsername())
        .password(providedUser.getPassword())
        .build();

    User updatedUser = userRepository.save(user);

    return userToUserInstanceResponse(updatedUser);
  }

  public DeleteResponse deleteUser(Long id) {
    User user = getUserById(id);

    userRepository.delete(user);

    DeleteResponse response = DeleteResponse.builder()
        .id(id)
        .message("User deleted")
        .build();

    return response;
  }

  private UserInstanceResponse userToUserInstanceResponse(User user) {
    UserInstanceResponse response = UserInstanceResponse.builder()
        .name_surname(user.getName_surname())
        .build();
    return response;
  }
}
