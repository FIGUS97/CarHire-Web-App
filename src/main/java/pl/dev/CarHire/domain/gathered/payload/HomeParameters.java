package pl.dev.CarHire.domain.gathered.payload;

import lombok.Builder;
import lombok.Getter;
import pl.dev.CarHire.domain.city.payload.CityInstanceResponse;

import java.util.List;

@Builder
@Getter
public class HomeParameters {
    private List<CityInstanceResponse> cities;
    private List<String> carTypes;
}
