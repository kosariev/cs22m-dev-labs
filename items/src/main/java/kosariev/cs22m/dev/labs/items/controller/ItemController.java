package kosariev.cs22m.dev.labs.items.controller;

import kosariev.cs22m.dev.labs.items.model.ItemDTO;
import kosariev.cs22m.dev.labs.items.model.ItemEntity;
import kosariev.cs22m.dev.labs.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public @ResponseBody void addItem(@RequestBody ItemDTO itemDTO) {
        itemService.addItem(itemDTO);
    }

    @GetMapping("/all")
    public @ResponseBody List<ItemEntity> getItems() {
        return itemService.getItems();
    }
}
