package pl.dev.CarHire.hire;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.car.payload.CarInstanceResponse;
import pl.dev.CarHire.common.payload.DeleteResponse;
import pl.dev.CarHire.hire.payload.CarHireInstanceResponse;
import pl.dev.CarHire.user.User;
import pl.dev.CarHire.car.CarRepository;
import pl.dev.CarHire.city.CityRepository;
import pl.dev.CarHire.user.UserRepository;
import pl.dev.CarHire.hire.payload.CarHireCreateRequest;
import pl.dev.CarHire.hire.payload.CarHireUpdateRequest;
import pl.dev.CarHire.user.payload.UserInstanceResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@ApplicationScope
public class CarHireService {

    /*
        - TODO: Test obsługi zależności w bazie danych
        - TODO: Obsługa błędów http
        - TODO: Mappery

     */

  @Autowired
  private CarHireRepository carHireRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CarRepository carRepository;

  public List<CarHire> getAllCarHires() {
    return carHireRepository.findAll();
  }

  public CarHire getCarHireById(Long id) {
    return carHireRepository.findById(id).get();
  }

  public List<CarHireInstanceResponse> findBy(Long userId, Long carId, String status, Integer days, Float price) {

    List<CarHire> hires = carHireRepository.findByAttributes(userId, carId, status, days, price);

    List<CarHireInstanceResponse> responses = new ArrayList<>();
    hires.forEach(hire -> responses.add(carHireToCarHireInstanceResponse(hire)));

    return responses;
  }

  public CarHireInstanceResponse addCarHire(CarHireCreateRequest newCarHire) throws HttpResponseException {
    User customer = userRepository.getById(newCarHire.getUserId());
    Car car = carRepository.getById(newCarHire.getCarId());

    CarHire carHire = CarHire.builder()
        .customer(customer)
        .car(car)
        .status(newCarHire.getStatus())
        .number_days(newCarHire.getDays())
        .amount(newCarHire.getPrice())
        .build();


    CarHire savedCarHire = carHireRepository.save(carHire);
    car.getCarHires().add(savedCarHire);

    CarHireInstanceResponse response = carHireToCarHireInstanceResponse(savedCarHire);

    return response;
  }

  public CarHireInstanceResponse updateCarHire(CarHireUpdateRequest providedCarHire) {

    CarHire currentCarHire = carHireRepository.getById(providedCarHire.getId());

    CarHire updatedCarHire = CarHire.builder()
        .id(providedCarHire.getId())
        .status(providedCarHire.getStatus())
        .number_days(providedCarHire.getDays())
        .amount(providedCarHire.getPrice())
        .car(currentCarHire.getCar())
        .customer(currentCarHire.getCustomer())
        .build();

    CarHire carHire = carHireRepository.save(updatedCarHire);

    CarHireInstanceResponse response = carHireToCarHireInstanceResponse(updatedCarHire);

    return response;
  }

  public DeleteResponse deleteCarHire(Long id) {
    CarHire carHire = getCarHireById(id);

    //carHire.getCar().getCarHires().remove(carHire);
    carHireRepository.delete(carHire);

    DeleteResponse response = DeleteResponse.builder()
        .id(id)
        .message("Car hire deleted.")
        .build();
    return response;
  }

  private CarHireInstanceResponse carHireToCarHireInstanceResponse (CarHire hire) {
    CarHireInstanceResponse response = CarHireInstanceResponse.builder()
        .car(CarInstanceResponse.builder()
            .brand(hire.getCar().getBrand())
            .model(hire.getCar().getModel())
            .status(hire.getCar().getStatus())
            .cityName(hire.getCar().getCity().getName())
            .price(hire.getCar().getPrice_per_day())
            .build())
        .id(hire.getId())
        .status(hire.getStatus())
        .price(hire.getAmount())
        .days(hire.getNumber_days())
        .user(UserInstanceResponse.builder()
            .name_surname(hire.getCustomer().getName_surname())
            .build())
        .build();
    return response;
  }
}