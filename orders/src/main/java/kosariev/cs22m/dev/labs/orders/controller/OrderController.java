package kosariev.cs22m.dev.labs.orders.controller;

import kosariev.cs22m.dev.labs.orders.model.OrderDTO;
import kosariev.cs22m.dev.labs.orders.model.OrderEntity;
import kosariev.cs22m.dev.labs.orders.service.OrderService;
import kosariev.cs22m.dev.labs.orders.service.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public @ResponseBody ResponseDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @GetMapping("/{user_id}/{order_id}")
    public @ResponseBody OrderEntity getOrder(@PathVariable("user_id") int user_id, @PathVariable("order_id") int order_id) {
        try {
            return orderService.getOrder(user_id, order_id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{user_id}/all")
    public @ResponseBody List<OrderEntity> getOrdersByUser(@PathVariable("user_id") int user_id) {
        return orderService.getOrdersByUser(user_id);
    }
}
