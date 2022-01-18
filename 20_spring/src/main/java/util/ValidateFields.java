package util;

import dto.UserAddDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Component
public class ValidateFields {

    public BindingResult validateFields(@Valid UserAddDTO user, BindingResult result, HttpServletRequest req) {

        if (!user.getPassword().equals(user.getPasswordAgain())) {
            result.rejectValue("confirmPassword", "error.user", "Password and confirm password are different");
        }


        return result;
    }


}
