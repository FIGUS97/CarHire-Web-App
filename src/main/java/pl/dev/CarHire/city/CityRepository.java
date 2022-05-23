package pl.dev.CarHire.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dev.CarHire.city.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

  City findByName(@Param("name") String name);
}
