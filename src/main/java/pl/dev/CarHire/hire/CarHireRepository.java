package pl.dev.CarHire.hire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dev.CarHire.hire.CarHire;

@Repository
public interface CarHireRepository extends JpaRepository<CarHire, Long> {

  @Query("SELECT c FROM CarHire c WHERE "
      + "(:userId is null or c.customer = :userId) and "
      + "(:carId is null or c.car = :carId) and "
      + "(:status is null or c.status = :status) and "
      + "(:days is null or c.number_days = :days) and "
      + "(:price is null or c.amount = :price)")
  List<CarHire> findByAttributes(
        @Param("userId") Long userId,
        @Param("carId") Long carId,
        @Param("status") String status,
        @Param("days") Integer days,
        @Param("price") Float price);
}