package pl.dev.CarHire.exposure.spring.data.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import pl.dev.CarHire.exposure.model.Car;
import pl.dev.CarHire.exposure.model.City;
import pl.dev.CarHire.exposure.spring.data.Repository.CarRepository;
import pl.dev.CarHire.exposure.spring.data.Repository.CityRepository;
import pl.dev.CarHire.exposure.spring.payload.request.create.CityCreateRequest;
import pl.dev.CarHire.exposure.spring.payload.request.update.CityUpdateRequest;

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
