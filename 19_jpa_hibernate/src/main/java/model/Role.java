package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Модель роли с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "users")
@Entity
@Table(name = "role")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @NotNull
    private Long id;

    @Column(name = "role_name", length = 64, nullable = false)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(@NotBlank String name) {
        this.name = name;
    }

    public Role(@NotNull Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }
}
