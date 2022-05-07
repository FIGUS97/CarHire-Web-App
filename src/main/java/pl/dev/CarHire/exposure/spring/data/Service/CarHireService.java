package pl.dev.CarHire.exposure.spring.data.Service;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.exposure.model.Car;
import pl.dev.CarHire.exposure.model.CarHire;
import pl.dev.CarHire.exposure.model.User;
import pl.dev.CarHire.exposure.spring.data.Repository.CarHireRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.CarRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.CityRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.UserRepository;
import pl.dev.CarHire.exposure.spring.payload.request.create.CarHireCreateRequest;
import pl.dev.CarHire.exposure.spring.payload.request.update.CarHireUpdateRequest;

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

  public List<CarHire> findBy(Long userId, Long carId, String status, Integer days, Float price) {
    return carHireRepository.findByAttributes(userId, carId, status, days, price);
  }

  public CarHire addCarHire(CarHireCreateRequest newCarHire) throws HttpResponseException {
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

    return savedCarHire;
  }

  public CarHire updateCarHire(CarHireUpdateRequest providedCarHire) {

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

    return carHire;
  }

  public String deleteCarHire(Long id) {
    CarHire carHire = getCarHireById(id);

    //carHire.getCar().getCarHires().remove(carHire);
    carHireRepository.delete(carHire);

    return "Deleted";
  }
}