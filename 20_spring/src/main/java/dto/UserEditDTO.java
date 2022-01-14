package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import model.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserEditDTO {
    @Size(min = 4, max = 64, message = "Login length must be from 4 to 64 characters")
    private String login;

    private String password;

    private String confirmPassword;
    @Email
    private String email;
    @Size(min = 2, max = 64, message = "First name must be from 2 to 64 characters")
    private String firstName;
    @Size(min = 2, max = 64, message = "First name must be from 2 to 64 characters")
    private String lastName;
    @NotNull(message = "Birthday is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull(message = "role is required")
    private Role role;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
