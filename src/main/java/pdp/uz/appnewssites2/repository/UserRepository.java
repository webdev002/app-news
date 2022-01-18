package pdp.uz.appnewssites2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.appnewssites2.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
