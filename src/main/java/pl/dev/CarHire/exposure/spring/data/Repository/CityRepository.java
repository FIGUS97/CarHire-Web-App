package pl.dev.CarHire.exposure.spring.data.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dev.CarHire.exposure.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  City findByName(@Param("name") String name);
}
