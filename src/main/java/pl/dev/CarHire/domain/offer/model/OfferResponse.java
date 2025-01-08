package pl.dev.CarHire.domain.offer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.city.payload.CityInstanceResponse;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponse {
    private CarInstanceResponse car;
    private CityInstanceResponse city;
    private UserInstanceResponse user;
    private String description;
}
