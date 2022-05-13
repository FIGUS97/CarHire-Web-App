package pl.dev.CarHire.car;

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
import pl.dev.CarHire.car.payload.CarCreateRequest;
import pl.dev.CarHire.common.payload.DeleteResponse;
import pl.dev.CarHire.car.payload.CarInstanceResponse;
import pl.dev.CarHire.car.payload.CarUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    /* TODOs:
        - TODO: Obsługa błędów
        - TODO: Dodanie Security (token JWT)
        - TODO: Dodanie swaggera
        - TODO: Przerobienie endpointów na biznesowe
    */

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @Operation(summary = "Getting all cars", description = "Just getting all cars")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<List<CarInstanceResponse>> getAllCars(@RequestParam(value = "brand") Optional<String> brand, @RequestParam(value = "status") Optional<String> status) {
        List<CarInstanceResponse> response = carService.findBy(brand.orElse(null), status.orElse(null));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById( @PathVariable Long id) {
        Car response = carService.getCarById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars")
    public ResponseEntity<CarInstanceResponse> addCar(@RequestBody CarCreateRequest newCar) throws HttpResponseException {
        CarInstanceResponse response = carService.addCar(newCar);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cars")
    public ResponseEntity<CarInstanceResponse> editCar(@RequestBody CarUpdateRequest updatedCar) {
        CarInstanceResponse response = carService.updateCar(updatedCar);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<DeleteResponse> deleteCar(@PathVariable Long id) {
        DeleteResponse response = carService.deleteCar(id);
        return ResponseEntity.ok(response);
    }

}
