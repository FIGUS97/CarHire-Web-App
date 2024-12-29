package pl.dev.CarHire.domain.city;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.dev.CarHire.domain.car.CarRepository;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.city.exception.NoSuchCityException;
import pl.dev.CarHire.domain.city.payload.CityCreateRequest;
import pl.dev.CarHire.domain.city.payload.CityInstanceResponse;
import pl.dev.CarHire.domain.city.payload.CityUpdateRequest;
import pl.dev.CarHire.domain.common.payload.DeleteResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ApplicationScope
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CarRepository carRepository;

  private ModelMapper modelMapper;

  public CityService() {
    this.modelMapper = new ModelMapper();
  }

  public List<CarInstanceResponse> getCarsForCity(String cityName) {
    return carRepository
        .findAllByCityName(cityName)
        .stream()
        .map(car -> modelMapper.map(car, CarInstanceResponse.class))
        .collect(Collectors.toList());
  }

  public List<CityInstanceResponse> findAllBy(Optional<String> cityName) {

    List<City> cities = cityName.map(s ->
            Collections.singletonList(cityRepository.findByName(s).orElseThrow(() -> new NoSuchCityException(s))))
            .orElseGet(cityRepository::findAll);

    return cities
        .stream()
        .map(city -> modelMapper.map(city, CityInstanceResponse.class))
        .collect(Collectors.toList());
  }

  public City getCityById(String id) {
    return cityRepository.findById(id).get();
  }

  public CityInstanceResponse addCity(CityCreateRequest newCity) {
    City city = City.builder()
        .name(newCity.getName())
        .build();

    City created = cityRepository.save(city);

    return modelMapper.map(created, CityInstanceResponse.class);
  }

  public DeleteResponse deleteCity(String id) {
    City city = getCityById(id);

    cityRepository.delete(city);

    return DeleteResponse.builder()
        .id(id)
        .message("City deleted.")
        .build();
  }

  public CityInstanceResponse updateCity(CityUpdateRequest updatedCity) {
    City city = City.builder()
        .id(updatedCity.getId())
        .name(updatedCity.getName())
        .build();

    City updated = cityRepository.save(city);

    return modelMapper.map(updated, CityInstanceResponse.class);
  }
}
