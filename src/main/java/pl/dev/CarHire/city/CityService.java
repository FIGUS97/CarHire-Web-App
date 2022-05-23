package pl.dev.CarHire.city;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import pl.dev.CarHire.car.Car;
import pl.dev.CarHire.car.CarRepository;
import pl.dev.CarHire.car.payload.CarInstanceResponse;
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

  private ModelMapper modelMapper;

  public CityService() {
    this.modelMapper = new ModelMapper();
  }

  public List<CarInstanceResponse> getCarsForCity(String cityName) {
    List<CarInstanceResponse> cars = carRepository
        .findAllByCityName(cityName)
        .stream()
        .map(car -> modelMapper.map(car, CarInstanceResponse.class))
        .collect(Collectors.toList());

    return cars;
  }

  public List<CityInstanceResponse> findAllBy(Optional<String> cityName) {

    List<City> cities = cityName.map(s ->
            Collections.singletonList(cityRepository.findByName(s)))
            .orElseGet(() -> cityRepository.findAll());

    List<CityInstanceResponse> responses = cities
        .stream()
        .map(city -> modelMapper.map(city, CityInstanceResponse.class))
        .collect(Collectors.toList());

    return responses;
  }

  public City getCityById(String id) {
    return cityRepository.findById(id).get();
  }

  public CityInstanceResponse addCity(CityCreateRequest newCity) {
    City city = City.builder()
        .name(newCity.getName())
        .build();

    City created = cityRepository.save(city);

    CityInstanceResponse response = modelMapper.map(city, CityInstanceResponse.class);

    return response;
  }

  public DeleteResponse deleteCity(String id) {
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

    CityInstanceResponse response = modelMapper.map(city, CityInstanceResponse.class);

    return response;
  }
}
