package pl.dev.CarHire.domain.car;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dev.CarHire.domain.car.exception.NoSuchCarException;
import pl.dev.CarHire.domain.car.payload.CarCreateRequest;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.car.payload.CarUpdateRequest;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/car/{offerId}")
    @Operation(summary = "Get the car of the offer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer is found and car is delivered", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<CarInstanceResponse> getOffersCar(
            @PathVariable(value = "offerId") String offerId) {
        return ResponseEntity.ok(carService.findBy(offerId));
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
