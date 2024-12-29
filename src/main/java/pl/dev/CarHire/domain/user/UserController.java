package pl.dev.CarHire.domain.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;
import pl.dev.CarHire.domain.user.exception.UserNotFoundException;
import pl.dev.CarHire.domain.user.payload.UserCreateRequest;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;
import pl.dev.CarHire.domain.user.payload.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  @GetMapping("/users")
  @Operation(summary = "Getting all users", description = "Just getting all users")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<List<UserInstanceResponse>> getAllUsers(
      @RequestParam(value = "role") Optional<String> role,
      @RequestParam(value = "city") Optional<String> city,
      @RequestParam(value = "name_surname") Optional<String> nameSurname,
      @RequestParam(value = "status") Optional<String> status,
      @RequestParam(value = "email") Optional<String> email,
      @RequestParam(value = "username") Optional<String> username) throws NoSuchCityException {
    List<UserInstanceResponse> responses = userService.findBy(
        role.orElse(null),
        city.orElse(null),
        nameSurname.orElse(null),
        status.orElse(null),
        email.orElse(null),
        username.orElse(null));

    return ResponseEntity.ok(responses);
  }

  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable String id) throws UserNotFoundException { return userService.getUserById(id); }

  @PostMapping("/users")
  public ResponseEntity<UserInstanceResponse> addUser(@RequestBody UserCreateRequest newUser)
      throws NoSuchCityException {
    UserInstanceResponse response = userService.addUser(newUser);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/users")
  public ResponseEntity<UserInstanceResponse> editUser(@RequestBody UserUpdateRequest updatedCar) throws NoSuchCityException {
    UserInstanceResponse response = userService.updateUser(updatedCar);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<DeleteResponse> deleteCar(@PathVariable String id) throws UserNotFoundException {
    DeleteResponse response = userService.deleteUser(id);
    return ResponseEntity.ok(response);
  }

}
