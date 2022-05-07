package pl.dev.CarHire.city;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.car.CarRepository;
import pl.dev.CarHire.city.payload.CityCreateRequest;
import pl.dev.CarHire.city.payload.CityUpdateRequest;

@Service
@ApplicationScope
public class CityService {
      /*
        - TODO: Obsługa błędów http
        - TODO: Mappery
     */

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CarRepository carRepository;

  public List<Car> getCarsForCity(String cityName) {
    return carRepository.findAllByCityName(cityName);
  }

  public List<City> findAllBy(Optional<String> cityName) {
    return cityName.map(s -> Collections
        .singletonList(cityRepository.findByName(s))).orElseGet(() -> cityRepository.findAll());
  }

  public City getCityById(Long id) {
    return cityRepository.findById(id).get();
  }

  public City addCity(CityCreateRequest newCity) {
    City city = City.builder()
        .name(newCity.getName())
        .build();

    return cityRepository.save(city);
  }

  public String deleteCity(Long id) {
    City city = getCityById(id);

    cityRepository.delete(city);
    return "Deleted";
  }

  public City updateCity(CityUpdateRequest updatedCity) {
    City city = City.builder()
        .id(updatedCity.getId())
        .name(updatedCity.getName())
        .build();

    return cityRepository.save(city);
  }
}
