package pl.dev.CarHire.model.hire;

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
import pl.dev.CarHire.model.common.payload.DeleteResponse;
import pl.dev.CarHire.model.hire.payload.CarHireCreateRequest;
import pl.dev.CarHire.model.hire.payload.CarHireInstanceResponse;
import pl.dev.CarHire.model.hire.payload.CarHireUpdateRequest;

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
        - TODO: Przerobienie endpointów na biznesowe
    */

  public CarHireController(CarHireService carHireService) {
    this.carHireService = carHireService;
  }

  @GetMapping("/carHires")
  @Operation(summary = "Getting all car hires", description = "Just getting all car hires")
  @ApiResponses(value =  {
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
  })
  public ResponseEntity<List<CarHireInstanceResponse>> getAllCarHires(
      @RequestParam(value = "userId", required = false) Optional<String> userId,
      @RequestParam(value = "carId", required = false) Optional<String> carId,
      @RequestParam(value = "status", required = false) Optional<String> status,
      @RequestParam(value = "days", required = false) Optional<Integer> days,
      @RequestParam(value = "price", required = false) Optional<Float> price) {
    List<CarHireInstanceResponse> response = carHireService.findBy(
        userId.orElse(null),
        carId.orElse(null),
        status.orElse(null),
        days.orElse(null),
        price.orElse(null));

    return ResponseEntity.ok(response);
  }

  @GetMapping("/carHires/{id}")
  public ResponseEntity<CarHire> getCarHireById( @PathVariable String id) {

    CarHire response = carHireService.getCarHireById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/carHires")
  public ResponseEntity<CarHireInstanceResponse> addCarHire(@RequestBody CarHireCreateRequest newCarHire) throws HttpResponseException {

    CarHireInstanceResponse response = carHireService.addCarHire(newCarHire);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/carHires")
  public ResponseEntity<CarHireInstanceResponse> editCarHire(@RequestBody CarHireUpdateRequest updatedCarHire) {
    CarHireInstanceResponse response = carHireService.updateCarHire(updatedCarHire);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/carHires/{id}")
  public ResponseEntity<DeleteResponse> deleteCarHire(@PathVariable String id) {
    DeleteResponse response = carHireService.deleteCarHire(id);
    return ResponseEntity.ok(response);
  }

}
