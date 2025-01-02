package pl.dev.CarHire.domain.offer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferItemsRequest {
    public String carType;
    public String city;
    public Date dateFrom;
    public Date dateTo;
}
