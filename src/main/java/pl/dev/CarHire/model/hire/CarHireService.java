package pl.dev.CarHire.model.hire;

import org.apache.http.client.HttpResponseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.model.car.Car;
import pl.dev.CarHire.model.car.payload.CarInstanceResponse;
import pl.dev.CarHire.model.common.payload.DeleteResponse;
import pl.dev.CarHire.model.hire.payload.CarHireInstanceResponse;
import pl.dev.CarHire.model.car.CarRepository;
import pl.dev.CarHire.model.city.CityRepository;
import pl.dev.CarHire.model.hire.payload.CarHireCreateRequest;
import pl.dev.CarHire.model.hire.payload.CarHireUpdateRequest;
import pl.dev.CarHire.model.user.UserRepository;
import pl.dev.CarHire.model.user.User;
import pl.dev.CarHire.model.user.payload.UserInstanceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class CarHireService {

  @Autowired
  private CarHireRepository carHireRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CarRepository carRepository;

  private final ModelMapper modelMapper;

  public CarHireService() {
    this.modelMapper = new ModelMapper();
  }

  public List<CarHire> getAllCarHires() {
    return carHireRepository.findAll();
  }

  public CarHire getCarHireById(String id) {
    return carHireRepository.findById(id).get();
  }

  public List<CarHireInstanceResponse> findBy(String userId, String carId, String status, Integer days, Float price) {

    List<CarHire> hires = carHireRepository.findByAttributes(userId, carId, status, days, price);

    List<CarHireInstanceResponse> responses = new ArrayList<>();
    hires.forEach(hire -> responses.add(modelMapper.map(hire, CarHireInstanceResponse.class)));

    return responses;
  }

  public CarHireInstanceResponse addCarHire(CarHireCreateRequest newCarHire) {
    User customer = userRepository.getById(newCarHire.getUserId());
    Car car = carRepository.getById(newCarHire.getCarId());

    CarHire carHire = CarHire.builder()
        .customer(customer)
        .car(car)
        .status(newCarHire.getStatus())
        .days(newCarHire.getDays())
        .price(newCarHire.getPrice())
        .build();


    CarHire savedCarHire = carHireRepository.save(carHire);
    car.getCarHires().add(savedCarHire);

    return modelMapper.map(savedCarHire, CarHireInstanceResponse.class);
  }

  public CarHireInstanceResponse updateCarHire(CarHireUpdateRequest providedCarHire) {

    CarHire currentCarHire = carHireRepository.getById(providedCarHire.getId());

    CarHire updatedCarHire = CarHire.builder()
        .id(providedCarHire.getId())
        .status(providedCarHire.getStatus())
        .days(providedCarHire.getDays())
        .price(providedCarHire.getPrice())
        .car(currentCarHire.getCar())
        .customer(currentCarHire.getCustomer())
        .build();

    CarHire carHire = carHireRepository.save(updatedCarHire);

    return modelMapper.map(carHire, CarHireInstanceResponse.class);
  }

  public DeleteResponse deleteCarHire(String id) {
    CarHire carHire = getCarHireById(id);

    carHireRepository.delete(carHire);

    return DeleteResponse.builder()
        .id(id)
        .message("Car hire deleted.")
        .build();
  }

  private CarHireInstanceResponse carHireToCarHireInstanceResponse (CarHire hire) {
    return CarHireInstanceResponse.builder()
        .car(CarInstanceResponse.builder()
            .brand(hire.getCar().getBrand())
            .model(hire.getCar().getModel())
            .status(hire.getCar().getStatus())
            .cityName(hire.getCar().getCity().getName())
            .pricePerDay(hire.getCar().getPricePerDay())
            .build())
        .id(hire.getId())
        .status(hire.getStatus())
        .price(hire.getPrice())
        .days(hire.getDays())
        .customer(UserInstanceResponse.builder()
            .name_surname(hire.getCustomer().getName_surname())
            .build())
        .build();
  }
}