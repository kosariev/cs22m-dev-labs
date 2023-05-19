package kosariev.cs22m.dev.labs.items.service;

import kosariev.cs22m.dev.labs.items.model.CategoryDTO;
import kosariev.cs22m.dev.labs.items.model.CategoryEntity;
import kosariev.cs22m.dev.labs.items.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(categoryDTO.getTitle());
        categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }
}
