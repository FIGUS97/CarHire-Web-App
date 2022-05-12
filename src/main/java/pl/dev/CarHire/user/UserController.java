package pl.dev.CarHire.user;

import java.util.List;
import java.util.Optional;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import pl.dev.CarHire.common.payload.DeleteResponse;
import pl.dev.CarHire.user.payload.UserCreateRequest;
import pl.dev.CarHire.user.payload.UserInstanceResponse;
import pl.dev.CarHire.user.payload.UserUpdateRequest;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
      /* TODOs:
        - TODO: Implementacja endpointów
        - TODO: Obsługa błędów
        - TODO: Dodanie DTOs
        - TODO: Dodanie Security (token JWT)
        - TODO: Dodanie swaggera
    */

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
      @RequestParam(value = "username") Optional<String> username) {
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
  public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }

  @PostMapping("/users")
  public ResponseEntity<UserInstanceResponse> addUser(@RequestBody UserCreateRequest newUser) throws HttpResponseException {
    UserInstanceResponse response = userService.addUser(newUser);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/users")
  public ResponseEntity<UserInstanceResponse> editUser(@RequestBody UserUpdateRequest updatedCar) {
    UserInstanceResponse response = userService.updateUser(updatedCar);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<DeleteResponse> deleteCar(@PathVariable Long id) {
    DeleteResponse response = userService.deleteUser(id);
    return ResponseEntity.ok(response);
  }

}
