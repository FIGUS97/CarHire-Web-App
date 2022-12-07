package pl.dev.CarHire.model.car;

import org.apache.http.client.HttpResponseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import pl.dev.CarHire.model.car.exception.NoSuchCarException;
import pl.dev.CarHire.model.city.exception.NoSuchCityException;
import pl.dev.CarHire.model.common.payload.DeleteResponse;
import pl.dev.CarHire.model.car.payload.CarInstanceResponse;
import pl.dev.CarHire.model.city.City;
import pl.dev.CarHire.model.city.CityRepository;
import pl.dev.CarHire.model.car.payload.CarCreateRequest;
import pl.dev.CarHire.model.car.payload.CarUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ApplicationScope
public class CarService {

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

    public Car getCarById(String id) throws NoSuchCarException {
        return carRepository.findById(id).orElseThrow(() -> new NoSuchCarException(id));
    }

    public List<CarInstanceResponse> findBy( String brand, String status) {
        return carRepository
            .findByBrandAndStatus(brand, status)
            .stream()
            .map(car -> modelMapper.map(car, CarInstanceResponse.class))
            .collect(Collectors.toList());
    }

    public CarInstanceResponse addCar(CarCreateRequest newCar) throws NoSuchCityException {
        City city = cityRepository.findByName(newCar.getCityName()).orElseThrow(() -> new NoSuchCityException(newCar.getCityName()));

        Car car = Car.builder()
            .brand(newCar.getBrand())
            .model(newCar.getModel())
            .status(newCar.getStatus())
            .pricePerDay(newCar.getPrice())
            .city(city)
            .build();


        Car savedCar = carRepository.save(car);
        city.getCars().add(savedCar);

        return modelMapper.map(car, CarInstanceResponse.class);
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

        return modelMapper.map(car, CarInstanceResponse.class);
    }

    public DeleteResponse deleteCar(String id) throws NoSuchCarException {
        Car car = getCarById(id);

        carRepository.delete(car);

        return DeleteResponse.builder()
            .id(id)
            .message("Car deleted.")
            .build();
    }
}