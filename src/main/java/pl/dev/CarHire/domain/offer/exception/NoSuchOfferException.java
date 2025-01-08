package pl.dev.CarHire.domain.offer.exception;

public class NoSuchOfferException extends RuntimeException {
    public NoSuchOfferException(String offerId) {
        super("Offer with id: " + offerId + " not found!");
    }
}
