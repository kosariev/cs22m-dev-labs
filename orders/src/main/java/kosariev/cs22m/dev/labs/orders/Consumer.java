package kosariev.cs22m.dev.labs.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import kosariev.cs22m.dev.labs.orders.model.AmqpDTO;
import kosariev.cs22m.dev.labs.orders.model.OrderEntity;
import kosariev.cs22m.dev.labs.orders.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Slf4j
@Component
@EnableRabbit
public class Consumer {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AmqpTemplate template;

    @RabbitListener(queues = "order_user")
    public void onCustomer(final Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AmqpDTO amqpDTO = objectMapper.readValue(message.getBody(), AmqpDTO.class);
            Optional<OrderEntity> orderEntity = orderRepository.findById((long) amqpDTO.getId());
            if (orderEntity.isPresent()) {
                OrderEntity currentOrder = orderEntity.get();
                if (amqpDTO.getState() == -1) {
                    currentOrder.setStatus(OrderEntity.Status.USER_NOT_FOUND);
                    orderRepository.save(currentOrder);
                }
                template.convertAndSend("item", amqpDTO);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "order_item")
    public void onItem(final Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AmqpDTO amqpDTO = objectMapper.readValue(message.getBody(), AmqpDTO.class);
            Optional<OrderEntity> orderEntity = orderRepository.findById((long) amqpDTO.getId());
            if (orderEntity.isPresent()) {
                OrderEntity currentOrder = orderEntity.get();
                OrderEntity.Status currentStatus = currentOrder.getStatus();
                if (currentStatus != OrderEntity.Status.USER_NOT_FOUND) {
                    if (amqpDTO.getState() == -1) {
                        currentOrder.setStatus(OrderEntity.Status.OUT_OF_STOCK);
                        orderRepository.save(currentOrder);
                    } else {
                        currentOrder.setStatus(OrderEntity.Status.ACCEPTED);
                        orderRepository.save(currentOrder);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
