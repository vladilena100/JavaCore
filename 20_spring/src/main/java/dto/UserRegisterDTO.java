package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserRegisterDTO {
    @Size(min = 4, max = 64, message = "Login length must be from 4 to 64 characters")
    private String login;
    @Size(min = 4, max = 64, message = "Password length must be from 4 to 64 characters")
    private String password;
    @Size(min = 4, max = 64, message = "Password length must be from 4 to 64 characters")
    private String passwordAgain;
    @Email(message = "email is not correct")
    private String email;
    @Size(min = 2, max = 64, message = "First name must be from 2 to 64 characters")
    private String firstName;
    @Size(min = 2, max = 64, message = "First name must be from 2 to 64 characters")
    private String lastName;
    @NotNull(message = "Birthday is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

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

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
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
}
