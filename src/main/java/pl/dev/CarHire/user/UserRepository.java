package pl.dev.CarHire.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.dev.CarHire.city.City;
import pl.dev.CarHire.role.Role;
import pl.dev.CarHire.user.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT c FROM User c WHERE "
        + "(:role is null or c.role = :role) and "
        + "(:city is null or c.city = :city) and "
        + "(:nameSurname is null or c.name_surname like concat('%',:nameSurname,'%')) and "
        + "(:status is null or c.status = :status) and "
        + "(:email is null or c.email = :email) and "
        + "(:username is null or c.username = :username)")
    List<User> findByAttributes(
        @Param("role") Role role,
        @Param("city") City city,
        @Param("nameSurname") String nameSurname,
        @Param("status") String status,
        @Param("email") String email,
        @Param("username") String username);
}
