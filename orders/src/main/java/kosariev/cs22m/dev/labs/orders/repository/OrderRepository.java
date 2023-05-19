package kosariev.cs22m.dev.labs.orders.repository;

import kosariev.cs22m.dev.labs.orders.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserid(int user);
    Optional<OrderEntity> findByUseridAndId(int user, int order);
}
