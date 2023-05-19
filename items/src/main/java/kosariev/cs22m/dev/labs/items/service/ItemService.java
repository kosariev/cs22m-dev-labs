package kosariev.cs22m.dev.labs.items.service;

import kosariev.cs22m.dev.labs.items.model.ItemDTO;
import kosariev.cs22m.dev.labs.items.model.ItemEntity;
import kosariev.cs22m.dev.labs.items.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void addItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItem(itemDTO.getItem());
        itemEntity.setTitle(itemDTO.getTitle());
        itemEntity.setCategory(itemDTO.getCategory());
        itemEntity.setWarehouse(itemDTO.getWarehouse());
        itemEntity.setQuantity(itemDTO.getQuantity());
        itemRepository.saveAndFlush(itemEntity);
    }

    public List<ItemEntity> getItems() {
        return itemRepository.findAll();
    }

    public boolean checkQuantity(int item, int warehouse) {
        Optional<ItemEntity> itemEntity = itemRepository.findByItemAndWarehouseAndQuantityGreaterThan(item, warehouse, 0);
        if (itemEntity.isPresent()) {
            return true;
        }
        return false;
    }
}
