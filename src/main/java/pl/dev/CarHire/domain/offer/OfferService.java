package pl.dev.CarHire.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dev.CarHire.domain.offer.model.Offer;
import pl.dev.CarHire.domain.offer.model.OfferItemResponse;
import pl.dev.CarHire.domain.offer.model.OfferItemsRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<OfferItemResponse> getOfferItems(OfferItemsRequest body) {
        List<Offer> offersFound = offerRepository
                .findAllIdsByCarTypeAndCityName(body.getCity(), body.getCarType());

        return offersFound.stream().map(offer ->
                        OfferItemResponse.builder()
                                .owner(offer.offerer.username)
                                .pricePerDay(Double.toString(offer.pricePerDay))
                                .title(offer.title)
                                .build())
                .collect(Collectors.toList());
    }
}
