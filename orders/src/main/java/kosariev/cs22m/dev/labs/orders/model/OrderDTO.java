package kosariev.cs22m.dev.labs.orders.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private int item;
    private int category;
    private int warehouse;
    private int user;
    private OrderEntity.Status status;
    private Date created;
}
