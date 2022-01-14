package model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Модель пользователя с характерными полями
 *
 * @author Vladilena Vasilieva
 */

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
