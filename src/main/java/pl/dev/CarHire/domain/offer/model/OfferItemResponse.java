package pl.dev.CarHire.domain.offer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferItemResponse {
    public String offerId;
    public String title;
    public String owner;
    public String pricePerDay;
}
