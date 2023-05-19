package kosariev.cs22m.dev.labs.orders.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AmqpDTO {
    private int id;
    private int item;
    private int user;
    private int category;
    private int warehouse;
    private int state;
}
