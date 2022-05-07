package pl.dev.CarHire.hire;

import org.apache.http.client.HttpResponseException;
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
import pl.dev.CarHire.hire.payload.CarHireCreateRequest;
import pl.dev.CarHire.hire.payload.CarHireUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarHireController {

  private final CarHireService carHireService;

    /* TODOs:
        - TODO: Obsługa błędów
        - TODO: Dodanie DTOs
        - TODO: Dodanie Security (token JWT)
        - TODO: Dodanie swaggera
    */

  public CarHireController(CarHireService carHireService) {
    this.carHireService = carHireService;
  }

  @GetMapping("/carHires")
  @Operation(summary = "Getting all cars", description = "Just getting all cars")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public List<CarHire> getAllCars(
      @RequestParam(value = "userId", required = false) Optional<Long> userId,
      @RequestParam(value = "carId", required = false) Optional<Long> carId,
      @RequestParam(value = "status", required = false) Optional<String> status,
      @RequestParam(value = "days", required = false) Optional<Integer> days,
      @RequestParam(value = "price", required = false) Optional<Float> price) {
    return carHireService.findBy(
        userId.orElse(null),
        carId.orElse(null),
        status.orElse(null),
        days.orElse(null),
        price.orElse(null));
  }

  @GetMapping("/carHires/{id}")
  public CarHire getCarHireById( @PathVariable Long id) { return carHireService.getCarHireById(id); }

  @PostMapping("/carHires")
  public CarHire addCarHire(@RequestBody CarHireCreateRequest newCarHire) throws HttpResponseException {
    return carHireService.addCarHire(newCarHire);
  }

  @PutMapping("/carHires")
  public CarHire editCarHire(@RequestBody CarHireUpdateRequest updatedCarHire) {
    return carHireService.updateCarHire(updatedCarHire);
  }

  @DeleteMapping("/carHires/{id}")
  public String deleteCarHire(@PathVariable Long id) {
    return carHireService.deleteCarHire(id);
  }

}
