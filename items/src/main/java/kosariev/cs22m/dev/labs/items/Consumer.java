package kosariev.cs22m.dev.labs.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import kosariev.cs22m.dev.labs.items.model.AmqpDTO;
import kosariev.cs22m.dev.labs.items.model.ItemEntity;
import kosariev.cs22m.dev.labs.items.repository.ItemRepository;
import kosariev.cs22m.dev.labs.items.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@EnableRabbit
public class Consumer {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AmqpTemplate template;

    @RabbitListener(queues = "item")
    public void onOrderItem(final Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AmqpDTO amqpDTO = objectMapper.readValue(message.getBody(), AmqpDTO.class);

            boolean ifAvailable = itemService.checkQuantity(amqpDTO.getItem(), amqpDTO.getWarehouse());
            amqpDTO.setState(-1);

            if (ifAvailable) {
                Optional<ItemEntity> optionalEntity = itemRepository.findByItemAndWarehouseAndQuantityGreaterThan(amqpDTO.getItem(), amqpDTO.getWarehouse(), 0);
                if (optionalEntity.isPresent()) {
                    ItemEntity itemEntity = optionalEntity.get();
                    int itemEntityQuantity = itemEntity.getQuantity();
                    itemEntityQuantity = itemEntityQuantity - 1;
                    itemEntity.setQuantity(itemEntityQuantity);
                    itemRepository.saveAndFlush(itemEntity);
                    amqpDTO.setState(1);
                }
            }
            template.convertAndSend("order_item", amqpDTO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
