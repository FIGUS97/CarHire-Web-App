package pl.dev.CarHire.car;

import org.apache.http.client.HttpResponseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import pl.dev.CarHire.common.payload.DeleteResponse;
import pl.dev.CarHire.car.payload.CarInstanceResponse;
import pl.dev.CarHire.city.City;
import pl.dev.CarHire.city.CityRepository;
import pl.dev.CarHire.car.payload.CarCreateRequest;
import pl.dev.CarHire.car.payload.CarUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ApplicationScope
public class CarService {

    /*
        - TODO: Test obsługi zależności w bazie danych
        - TODO: Obsługa błędów http
        - TODO: Mappery
     */

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CityRepository cityRepository;

    private final ModelMapper modelMapper;

    public CarService() {
        this.modelMapper = new ModelMapper();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    public List<CarInstanceResponse> findBy( String brand, String status) {
        List<CarInstanceResponse> cars = carRepository
            .findByBrandAndStatus(brand, status)
            .stream()
            .map(car -> modelMapper.map(car, CarInstanceResponse.class))
            .collect(Collectors.toList());

        return cars;
    }

    public CarInstanceResponse addCar(CarCreateRequest newCar) throws HttpResponseException {
        City city = cityRepository.findByName(newCar.getCityName());

        Car car = Car.builder()
            .brand(newCar.getBrand())
            .model(newCar.getModel())
            .status(newCar.getStatus())
            .pricePerDay(newCar.getPrice())
            .city(city)
            .build();


        Car savedCar = carRepository.save(car);
        city.getCars().add(savedCar);

        CarInstanceResponse response = modelMapper.map(car, CarInstanceResponse.class);

        return response;
    }

    public CarInstanceResponse updateCar(CarUpdateRequest providedCar) {

        City city = cityRepository.getById(providedCar.getCityId());

        Car updatedCar = Car.builder()
            .id(providedCar.getId())
            .brand(providedCar.getBrand())
            .model(providedCar.getModel())
            .status(providedCar.getStatus())
            .city(city)
            .pricePerDay(providedCar.getPrice())
            .build();

        Car car = carRepository.save(updatedCar);

        CarInstanceResponse response = modelMapper.map(car, CarInstanceResponse.class);

        return response;
    }

    public DeleteResponse deleteCar(Long id) {
        Car car = getCarById(id);

        carRepository.delete(car);

        DeleteResponse response = DeleteResponse.builder()
            .id(id)
            .message("Car deleted.")
            .build();

        return response;
    }
}