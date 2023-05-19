package kosariev.cs22m.dev.labs.customers.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CUSTOMER")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "integer auto_increment")
    private Long id;

    @Column
    private String login;

    @Column
    private String password;
}
