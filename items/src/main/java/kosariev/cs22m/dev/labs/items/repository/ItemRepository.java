package kosariev.cs22m.dev.labs.items.repository;

import kosariev.cs22m.dev.labs.items.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByItemAndWarehouseAndQuantityGreaterThan(int item, int warehouse, int quantity);
    Optional<ItemEntity> findByCategoryAndWarehouseAndQuantityGreaterThan(int category, int warehouse, int quantity);
}
