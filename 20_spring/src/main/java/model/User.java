package model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Модель пользователя с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 4, max = 64)
    @Column(name = "login", length = 64, unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 4, max = 64)
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @NotBlank
//    @Size(min = 4, message = "name must be min 2 symbols")
    @Column(name = "first_name", nullable = false, length = 64)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 64)
    @Column(name = "last_name", nullable = false, length = 64)
    private String lastName;

    @Past
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
