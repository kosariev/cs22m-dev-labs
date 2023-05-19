package kosariev.cs22m.dev.labs.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kosariev.cs22m.dev.labs.customers.model.AmqpDTO;
import kosariev.cs22m.dev.labs.customers.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableRabbit
public class Consumer {

    @Autowired
    UserService userService;

    @Autowired
    AmqpTemplate template;

    @RabbitListener(queues = "order")
    public void onOrderUser(final Message message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AmqpDTO amqpDTO = objectMapper.readValue(message.getBody(), AmqpDTO.class);
            boolean ifExists = userService.checkUserById((long) amqpDTO.getUser());
            amqpDTO.setState(-1);
            if (ifExists) {
                amqpDTO.setState(1);
            }
            template.convertAndSend("order_user", amqpDTO);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
