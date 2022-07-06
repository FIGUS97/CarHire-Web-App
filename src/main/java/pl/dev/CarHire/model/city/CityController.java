package pl.dev.CarHire.model.city;

import java.util.List;
import java.util.Optional;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
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
import pl.dev.CarHire.model.car.payload.CarInstanceResponse;
import pl.dev.CarHire.model.city.payload.CityCreateRequest;
import pl.dev.CarHire.model.city.payload.CityInstanceResponse;
import pl.dev.CarHire.model.city.payload.CityUpdateRequest;
import pl.dev.CarHire.model.common.payload.DeleteResponse;

@RestController
public class CityController {
  @Autowired
  private CityService cityService;

      /* TODOs:
        - TODO: Obsługa błędów
        - TODO: Dodanie DTOs
        - TODO: Dodanie Security (token JWT)
        - TODO: Opisy do swaggera
        - TODO: Przerobienie endpointów na biznesowe
    */

  @GetMapping("/{cityName}/cars")
  public ResponseEntity<List<CarInstanceResponse>> getCars(@PathVariable(value = "cityName") String cityName) {
    List<CarInstanceResponse> response = cityService.getCarsForCity(cityName);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/cities")
  @Operation(summary = "Getting all cities", description = "Just getting all cities")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<List<CityInstanceResponse>> getAllCities(@RequestParam(value = "name") Optional<String> name) {
    List<CityInstanceResponse> response = cityService.findAllBy(name);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/cities/{id}")
  public ResponseEntity<City> getCityById( @PathVariable String id) {
    City city = cityService.getCityById(id);
    return ResponseEntity.ok(city);
  }

  @PostMapping("/cities")
  public ResponseEntity<CityInstanceResponse> addCity(@RequestBody CityCreateRequest newCity) throws HttpResponseException {
    CityInstanceResponse city = cityService.addCity(newCity);
    return ResponseEntity.ok(city);
  }

  @PutMapping("/cities")
  public ResponseEntity<CityInstanceResponse> editCity(@RequestBody CityUpdateRequest updatedCity) {
    CityInstanceResponse city = cityService.updateCity(updatedCity);
    return ResponseEntity.ok(city);
  }

  @DeleteMapping("/cities/{id}")
  public ResponseEntity<DeleteResponse> deleteCity(@PathVariable String id) {
    DeleteResponse response = cityService.deleteCity(id);
    return ResponseEntity.ok(response);
  }
}
