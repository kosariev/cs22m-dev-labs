package kosariev.cs22m.dev.labs.items.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ITEMS")
@Setter
@Getter
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "integer auto_increment")
    private Long id;

    @Column
    private int item;

    @Column
    private String title;

    @Column
    private int category;

    @Column
    int warehouse;

    @Column
    int quantity;
}
