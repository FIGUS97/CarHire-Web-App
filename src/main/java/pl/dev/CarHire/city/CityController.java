package pl.dev.CarHire.city;

import java.util.List;
import java.util.Optional;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.city.payload.CityCreateRequest;
import pl.dev.CarHire.city.payload.CityUpdateRequest;

@RestController
@RequestMapping("/api")
public class CityController {
  @Autowired
  private CityService cityService;

      /* TODOs:
        - TODO: Implementacja endpointów
        - TODO: Obsługa błędów
        - TODO: Dodanie DTOs
        - TODO: Dodanie Security (token JWT)
        - TODO: Dodanie swaggera
    */

  @GetMapping("/{cityName}/cars")
  public List<Car> getCars(@PathVariable(value = "cityName") String cityName) {
    System.out.println(cityName);
    return cityService.getCarsForCity(cityName);
  }

  @GetMapping("/cities")
  @Operation(summary = "Getting all cities", description = "Just getting all cities")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public List<City> getAllCities(@RequestParam(value = "name") Optional<String> name) {
    return cityService.findAllBy(name);
  }

  @GetMapping("/cities/{id}")
  public City getCarById( @PathVariable Long id) { return cityService.getCityById(id); }

  @PostMapping("/cities")
  public City addCity(@RequestBody CityCreateRequest newCity) throws HttpResponseException {
    return cityService.addCity(newCity);
  }

  @PutMapping("/cities")
  public City editCity(@RequestBody CityUpdateRequest updatedCity) {
    return cityService.updateCity(updatedCity);
  }

  @DeleteMapping("/cities/{id}")
  public String deleteCity(@PathVariable Long id) {
    return cityService.deleteCity(id);
  }
}