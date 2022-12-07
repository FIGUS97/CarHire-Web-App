package pl.dev.CarHire.model.car;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import pl.dev.CarHire.model.car.exception.NoSuchCarException;
import pl.dev.CarHire.model.car.payload.CarCreateRequest;
import pl.dev.CarHire.model.city.exception.NoSuchCityException;
import pl.dev.CarHire.model.common.payload.DeleteResponse;
import pl.dev.CarHire.model.car.payload.CarInstanceResponse;
import pl.dev.CarHire.model.car.payload.CarUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @Operation(summary = "Getting all cars", description = "Just getting all cars")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<List<CarInstanceResponse>> getAllCars(
        @ApiParam(name = "brand", value = "The brand name of the car.")
        @RequestParam(value = "brand", required = false)
        Optional<String> brand,
        @ApiParam(name = "status", value = "Status of the car", allowableValues = "Free, Booked, SERVICE")
        @RequestParam(value = "status", required = false)
        Optional<String> status) {
        List<CarInstanceResponse> response = carService.findBy(brand.orElse(null), status.orElse(null));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "Get car info.", description = "Get car info based on just id.")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<Car> getCarById( @PathVariable String id) throws NoSuchCarException {
        Car response = carService.getCarById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars")
    @Operation(summary = "Register new car.", description = "Complete registration of a new car.")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<CarInstanceResponse> addCar(@RequestBody CarCreateRequest newCar)
        throws NoSuchCityException {
        CarInstanceResponse response = carService.addCar(newCar);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cars")
    @Operation(summary = "Edit already registered car.", description = "Possible edition of brand, model, city, price.")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<CarInstanceResponse> editCar(@RequestBody CarUpdateRequest updatedCar) {
        CarInstanceResponse response = carService.updateCar(updatedCar);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cars/{id}")
    @Operation(summary = "Delete car from database.", description = "Option only for administrator")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "200", description = "Object found and visible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<DeleteResponse> deleteCar(@PathVariable String id) throws NoSuchCarException {
        DeleteResponse response = carService.deleteCar(id);
        return ResponseEntity.ok(response);
    }

}
