package pl.dev.CarHire.exposure.spring.data.Controller;

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
import pl.dev.CarHire.exposure.model.Car;
import pl.dev.CarHire.exposure.spring.data.Service.CarService;
import pl.dev.CarHire.exposure.spring.payload.request.create.CarCreateRequest;
import pl.dev.CarHire.exposure.spring.payload.request.update.CarUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;

    /* TODOs:
        - TODO: Obsługa błędów
        - TODO: Dodanie DTOs
        - TODO: Dodanie Security (token JWT)
        - TODO: Dodanie swaggera
    */

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @Operation(summary = "Getting all cars", description = "Just getting all cars")
    @ApiResponses(value =  {
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public List<Car> getAllCars(@RequestParam(value = "brand") Optional<String> brand, @RequestParam(value = "status") Optional<String> status) {
        return carService.findBy(brand.orElse(null), status.orElse(null));
    }

    @GetMapping("/cars/{id}")
    public Car getCarById( @PathVariable Long id) { return carService.getCarById(id); }

    @PostMapping("/cars")
    public Car addCar(@RequestBody CarCreateRequest newCar) throws HttpResponseException {
        return carService.addCar(newCar);
    }

    @PutMapping("/cars")
    public Car editCar(@RequestBody CarUpdateRequest updatedCar) {
        return carService.updateCar(updatedCar);
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }

}
