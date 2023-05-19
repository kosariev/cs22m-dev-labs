package kosariev.cs22m.dev.labs.items.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CATEGORIES")
@Setter
@Getter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "integer auto_increment")
    private Long id;

    @Column
    private String title;
}
