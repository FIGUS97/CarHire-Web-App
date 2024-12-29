package pl.dev.CarHire.domain.hire;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.domain.car.Car;
import pl.dev.CarHire.domain.car.CarRepository;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.city.CityRepository;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;
import pl.dev.CarHire.domain.hire.payload.CarHireCreateRequest;
import pl.dev.CarHire.domain.hire.payload.CarHireInstanceResponse;
import pl.dev.CarHire.domain.hire.payload.CarHireUpdateRequest;
import pl.dev.CarHire.domain.user.User;
import pl.dev.CarHire.domain.user.UserRepository;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;

import java.util.ArrayList;
import java.util.List;

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

  public CarHireInstanceResponse getCarHireById(String id) {
    CarHire response = carHireRepository.findById(id).get();
    return carHireToCarHireInstanceResponse(response);
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
    CarHire carHire = carHireRepository.findById(id).get();

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