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
import pl.dev.CarHire.city.payload.CityInstanceResponse;
import pl.dev.CarHire.city.payload.CityUpdateRequest;
import pl.dev.CarHire.common.payload.DeleteResponse;

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

  public CityInstanceResponse addCity(CityCreateRequest newCity) {
    City city = City.builder()
        .name(newCity.getName())
        .build();

    City created = cityRepository.save(city);

    CityInstanceResponse response = CityInstanceResponse.builder()
        .id(created.getId())
        .name(created.getName())
        .build();

    return response;
  }

  public DeleteResponse deleteCity(Long id) {
    City city = getCityById(id);

    cityRepository.delete(city);

    DeleteResponse response = DeleteResponse.builder()
        .id(id)
        .message("City deleted.")
        .build();
    return response;
  }

  public CityInstanceResponse updateCity(CityUpdateRequest updatedCity) {
    City city = City.builder()
        .id(updatedCity.getId())
        .name(updatedCity.getName())
        .build();

    City updated = cityRepository.save(city);

    CityInstanceResponse response = CityInstanceResponse.builder()
        .id(updated.getId())
        .name(updated.getName())
        .build();

    return response;
  }
}
