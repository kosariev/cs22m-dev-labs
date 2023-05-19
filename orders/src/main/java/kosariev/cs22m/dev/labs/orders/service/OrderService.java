package kosariev.cs22m.dev.labs.orders.service;

import jakarta.transaction.Transactional;
import kosariev.cs22m.dev.labs.orders.model.AmqpDTO;
import kosariev.cs22m.dev.labs.orders.model.OrderDTO;
import kosariev.cs22m.dev.labs.orders.model.OrderEntity;
import kosariev.cs22m.dev.labs.orders.repository.OrderRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomersApi customersApi;

    @Autowired
    AmqpTemplate template;

    public List<OrderEntity> getOrdersByUser(int user) {
        boolean ifExists = customersApi.checkUserById(user);
        if (!ifExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");
        }
        return orderRepository.findAllByUserid(user);
    }

    public OrderEntity getOrder(int user, int order) {
        boolean ifExists = customersApi.checkUserById(user);
        if (!ifExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.");
        }
        return orderRepository.findByUseridAndId(user, order).orElseThrow();
    }

    public ResponseDTO addOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setItem(orderDTO.getItem());
        orderEntity.setCategory(orderDTO.getCategory());
        orderEntity.setWarehouse(orderDTO.getWarehouse());
        orderEntity.setUserid(orderDTO.getUser());
        orderEntity.setCreated(new Date());
        orderEntity.setStatus(OrderEntity.Status.NEW);
        orderRepository.saveAndFlush(orderEntity);

        AmqpDTO reqDTO = new AmqpDTO();
        reqDTO.setId(orderEntity.getId().intValue());
        reqDTO.setUser(orderEntity.getUserid());
        reqDTO.setItem(orderEntity.getItem());
        reqDTO.setWarehouse(orderEntity.getWarehouse());
        reqDTO.setCategory(orderEntity.getCategory());
        reqDTO.setState(0);

        template.convertAndSend("order", reqDTO);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Created successfully. Order id: " + orderEntity.getId());
        return responseDTO;
    }
}
