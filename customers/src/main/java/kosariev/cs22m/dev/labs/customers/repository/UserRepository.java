package kosariev.cs22m.dev.labs.customers.repository;

import kosariev.cs22m.dev.labs.customers.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
