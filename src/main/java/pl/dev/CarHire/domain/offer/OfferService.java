package pl.dev.CarHire.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dev.CarHire.domain.car.Car;
import pl.dev.CarHire.domain.car.CarService;
import pl.dev.CarHire.domain.car.payload.CarInstanceResponse;
import pl.dev.CarHire.domain.city.City;
import pl.dev.CarHire.domain.city.payload.CityInstanceResponse;
import pl.dev.CarHire.domain.offer.exception.NoSuchOfferException;
import pl.dev.CarHire.domain.offer.model.Offer;
import pl.dev.CarHire.domain.offer.model.OfferItemResponse;
import pl.dev.CarHire.domain.offer.model.OfferItemsRequest;
import pl.dev.CarHire.domain.offer.model.OfferResponse;
import pl.dev.CarHire.domain.user.User;
import pl.dev.CarHire.domain.user.payload.UserInstanceResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final CarService carService;

    public List<OfferItemResponse> getOfferItems(OfferItemsRequest body) {
        List<Offer> offersFound = offerRepository
                .findAllIdsByCarTypeAndCityName(body.getCity(), body.getCarType());

        return offersFound.stream().map(offer ->
                        OfferItemResponse.builder()
                                .offerId(offer.id)
                                .owner(offer.offerer.username)
                                .pricePerDay(Double.toString(offer.pricePerDay))
                                .title(offer.title)
                                .build())
                .collect(Collectors.toList());
    }

    public OfferResponse getOffer(String offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new NoSuchOfferException(offerId));

        Car car = offer.car;
        User offerer = offer.offerer;
        City city = offer.city;

        return OfferResponse.builder()
                .car(CarInstanceResponse.builder()
                        .brand(car.brand)
                        .cityName(car.city.name)
                        .model(car.model)
                        .build())
                .user(UserInstanceResponse.builder()
                        .name_surname(offerer.name_surname)
                        .build())
                .city(CityInstanceResponse.builder()
                        .id(city.id)
                        .name(city.name)
                        .build())
                .build();
    }
}
