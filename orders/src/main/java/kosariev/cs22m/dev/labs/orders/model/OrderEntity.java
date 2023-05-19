package kosariev.cs22m.dev.labs.orders.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="ORDERS")
@Setter
@Getter
public class OrderEntity {
    public enum Status {
        NEW, USER_NOT_FOUND, OUT_OF_STOCK, ACCEPTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "integer auto_increment")
    private Long id;

    @Column
    private int item;

    @Column
    private int category;

    @Column
    private int warehouse;

    @Column
    private int userid;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.DATE)
    private Date created;
}
