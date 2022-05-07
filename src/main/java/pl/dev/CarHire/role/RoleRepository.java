package pl.dev.CarHire.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dev.CarHire.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String name);
}
