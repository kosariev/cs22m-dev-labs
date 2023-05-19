package kosariev.cs22m.dev.labs.items.repository;

import kosariev.cs22m.dev.labs.items.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
