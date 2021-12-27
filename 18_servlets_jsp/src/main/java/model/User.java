package model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Модель пользователя с характерными полями
 *
 * @author Vladilena Vasilieva
 */

@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Long id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Date birthday;

    private Integer age;

    private Role role;

    public User(Long id, String login, String password, String email, String firstName, String lastName, Date birthday, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Date birthday) {
        this.age = (int) ChronoUnit.YEARS.between(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
