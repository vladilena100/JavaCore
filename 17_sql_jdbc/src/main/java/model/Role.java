package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Модель роли с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Long id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

}
