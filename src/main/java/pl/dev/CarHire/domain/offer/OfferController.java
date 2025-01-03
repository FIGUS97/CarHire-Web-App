package pl.dev.CarHire.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dev.CarHire.domain.offer.model.OfferItemResponse;
import pl.dev.CarHire.domain.offer.model.OfferItemsRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/offerItems")
    public List<OfferItemResponse> getOfferItems(@RequestBody OfferItemsRequest body) {
        return offerService.getOfferItems(body);
    }
}
