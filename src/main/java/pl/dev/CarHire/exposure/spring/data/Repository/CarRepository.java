package pl.dev.CarHire.exposure.spring.data.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dev.CarHire.exposure.model.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  List<Car> findAllByBrand(String brand);

  List<Car> findAllByStatus(String status);

  @Query("SELECT c FROM Car c WHERE (:brand is null or c.brand = :brand) and (:status is null or c.status = :status)")
  List<Car> findByBrandAndStatus(@Param("brand") String brand, @Param("status") String status);

  List<Car> findAllByCityName(String name);

}
