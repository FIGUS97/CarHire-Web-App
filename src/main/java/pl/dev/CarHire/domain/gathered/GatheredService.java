package pl.dev.CarHire.domain.gathered;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dev.CarHire.domain.car.CarService;
import pl.dev.CarHire.domain.city.CityService;
import pl.dev.CarHire.domain.gathered.payload.HomeParameters;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GatheredService {
    private final CarService carService;
    private final CityService cityService;

    public HomeParameters getHomeParams() {
        return HomeParameters.builder()
                .cities(cityService.findAllBy(Optional.empty()))
                .carTypes(carService.findAllTypes())
                .build();
    }
}
