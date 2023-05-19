package kosariev.cs22m.dev.labs.items.controller;

import kosariev.cs22m.dev.labs.items.model.CategoryDTO;
import kosariev.cs22m.dev.labs.items.model.CategoryEntity;
import kosariev.cs22m.dev.labs.items.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public @ResponseBody void addUser(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/all")
    public @ResponseBody List<CategoryEntity> getCategories() {
        return categoryService.getCategories();
    }
}
