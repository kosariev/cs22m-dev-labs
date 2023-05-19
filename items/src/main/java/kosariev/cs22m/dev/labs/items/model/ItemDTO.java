package kosariev.cs22m.dev.labs.items.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDTO {
    private int item;
    private String title;
    private int category;
    private int warehouse;
    private int quantity;
}
