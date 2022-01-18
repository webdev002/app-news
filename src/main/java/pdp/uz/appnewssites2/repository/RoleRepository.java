package pdp.uz.appnewssites2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appnewssites2.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);

}
