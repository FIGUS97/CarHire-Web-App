package pl.dev.CarHire.domain.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dev.CarHire.domain.offer.model.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, String> {
    @Query("SELECT o " +
            "FROM Offer o " +
            "JOIN o.car ca " +
            "JOIN o.city ci " +
            "WHERE ci.name = :cityName " +
            "AND ca.type = :carType")
    List<Offer> findAllIdsByCarTypeAndCityName(@Param("cityName") String cityName,
                                               @Param("carType") String carType);
}
