package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Модель роли с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable {

    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
