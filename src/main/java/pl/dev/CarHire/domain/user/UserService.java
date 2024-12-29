package pl.dev.CarHire.domain.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dev.CarHire.domain.city.City;
import pl.dev.CarHire.domain.city.CityRepository;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;
import pl.dev.CarHire.domain.role.Role;
import pl.dev.CarHire.domain.role.RoleRepository;
import pl.dev.CarHire.domain.user.exception.UserNotFoundException;
import pl.dev.CarHire.domain.user.payload.UserCreateRequest;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;
import pl.dev.CarHire.domain.user.payload.UserUpdateRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private RoleRepository roleRepository;

  private final ModelMapper modelMapper;

  public UserService() {
    this.modelMapper = new ModelMapper();
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(String id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  public List<UserInstanceResponse> findBy(String role, String cityName, String nameSurname, String status, String email, String username)
      throws NoSuchCityException {
    List<UserInstanceResponse> responses = new ArrayList<>();


    City citySearched = null;
    if(cityName != null) {
      citySearched = cityRepository.findByName(cityName).orElseThrow(() -> new NoSuchCityException(cityName));
    }

    List<User> users = userRepository.findByAttributes(roleRepository.findByName(role), citySearched,
        nameSurname, status, email, username);

    users.forEach(user -> responses.add(userToUserInstanceResponse(user)));

    return responses;
  }

  public UserInstanceResponse addUser(UserCreateRequest newUser) throws NoSuchCityException {
    City city = cityRepository.findByName(newUser.getCityName()).orElseThrow(() -> new NoSuchCityException(newUser.getCityName()));
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

    return modelMapper.map(savedUser, UserInstanceResponse.class);
  }

  public UserInstanceResponse updateUser(UserUpdateRequest providedUser) throws NoSuchCityException {

    City city = cityRepository.findByName(providedUser.getCityName()).orElseThrow(() -> new NoSuchCityException(providedUser.getCityName()));
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

    return modelMapper.map(updatedUser, UserInstanceResponse.class);
  }

  public DeleteResponse deleteUser(String id) throws UserNotFoundException {
    User user = getUserById(id);

    userRepository.delete(user);

    return DeleteResponse.builder()
        .id(id)
        .message("User deleted")
        .build();
  }

  private UserInstanceResponse userToUserInstanceResponse(User user) {
    return UserInstanceResponse.builder()
        .name_surname(user.getName_surname())
        .id(user.getId())
        .build();
  }
}
