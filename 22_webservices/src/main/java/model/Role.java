package model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Модель роли с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @NotNull
    private Long id;

    @Column(name = "role_name", length = 64, nullable = false)
    @NotBlank
    private String name;

    public Role(@NotBlank String name) {
        this.name = name;
    }

    public Role(@NotNull Long id, @NotBlank String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
