package pl.dev.CarHire.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.dev.CarHire.domain.offer.model.OfferItemResponse;
import pl.dev.CarHire.domain.offer.model.OfferItemsRequest;
import pl.dev.CarHire.domain.offer.model.OfferResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/offer/{id}")
    public OfferResponse getOffer(@PathVariable("id") String offerId) {
        return offerService.getOffer(offerId);
    }

    @PostMapping("/offerItems")
    public List<OfferItemResponse> getOfferItems(@RequestBody OfferItemsRequest body) {
        return offerService.getOfferItems(body);
    }
}
