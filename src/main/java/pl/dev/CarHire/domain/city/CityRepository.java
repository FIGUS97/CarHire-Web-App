package pl.dev.CarHire.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

  Optional<City> findByName(@Param("name") String name);
}
