package pl.dev.CarHire.domain.car;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.domain.car.exception.NoSuchCarException;
import pl.dev.CarHire.domain.car.payload.CarCreateRequest;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.car.payload.CarUpdateRequest;
import pl.dev.CarHire.domain.city.City;
import pl.dev.CarHire.domain.city.CityRepository;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;
import pl.dev.CarHire.domain.offer.OfferService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ApplicationScope
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final OfferService offerService;

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

    public List<String> findAllTypes() {
        return carRepository.findAllCarTypes();
    }
}