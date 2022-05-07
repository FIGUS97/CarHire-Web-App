package pl.dev.CarHire.exposure.spring.data.Service;

import java.util.List;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dev.CarHire.exposure.model.City;
import pl.dev.CarHire.exposure.model.Role;
import pl.dev.CarHire.exposure.model.User;
import pl.dev.CarHire.exposure.spring.data.Repository.CityRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.RoleRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.UserRepository;
import pl.dev.CarHire.exposure.spring.payload.request.create.UserCreateRequest;
import pl.dev.CarHire.exposure.spring.payload.request.update.UserUpdateRequest;

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

  public List<User> findBy(String role, String city, String nameSurname, String status, String email, String username) {
    return userRepository.findByAttributes(roleRepository.findByName(role), cityRepository.findByName(city), nameSurname, status, email, username);
  }

  public User addUser(UserCreateRequest newUser) throws HttpResponseException {
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

    return savedUser;
  }

  public User updateUser(UserUpdateRequest providedUser) {

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

    return userRepository.save(user);
  }

  public String deleteUser(Long id) {
    User user = getUserById(id);

    userRepository.delete(user);

    return "Deleted";
  }

}
