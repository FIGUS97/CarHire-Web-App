package pl.dev.CarHire.domain.city;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.city.payload.CityCreateRequest;
import pl.dev.CarHire.domain.city.payload.CityInstanceResponse;
import pl.dev.CarHire.domain.city.payload.CityUpdateRequest;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {
  @Autowired
  private CityService cityService;


  @GetMapping("/{cityName}/cars")
  @Operation(summary = "Getting all cars in the city", description = "If the car is registered to work in one city, then it is listed here.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<List<CarInstanceResponse>> getCars(@PathVariable(value = "cityName") String cityName) {
    List<CarInstanceResponse> response = cityService.getCarsForCity(cityName);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/cities")
  @Operation(summary = "Getting all cities", description = "Listing all cities registered.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<List<CityInstanceResponse>> getAllCities(@RequestParam(value = "name") Optional<String> name) {
    List<CityInstanceResponse> response = cityService.findAllBy(name);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/cities/{id}")
  @Operation(summary = "Getting a city with given id", description = "Lists all the details about the city.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<City> getCityById( @PathVariable String id) {
    City city = cityService.getCityById(id);
    return ResponseEntity.ok(city);
  }

  @PostMapping("/cities")
  @Operation(summary = "Adding new city to database", description = "Given all the details about the city, user is able to register it.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<CityInstanceResponse> addCity(@RequestBody CityCreateRequest newCity) {
    CityInstanceResponse city = cityService.addCity(newCity);
    return ResponseEntity.ok(city);
  }

  @PutMapping("/cities")
  @Operation(summary = "Editing the city in database", description = "Given all the details about the city, user is able to edit it.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<CityInstanceResponse> editCity(@RequestBody CityUpdateRequest updatedCity) {
    CityInstanceResponse city = cityService.updateCity(updatedCity);
    return ResponseEntity.ok(city);
  }

  @DeleteMapping("/cities/{id}")
  @Operation(summary = "Delete the city", description = "Option only for administrator.")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<DeleteResponse> deleteCity(@PathVariable String id) {
    DeleteResponse response = cityService.deleteCity(id);
    return ResponseEntity.ok(response);
  }
}
