//package util;
//
//import dto.UserAddDTO;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BindingResult;
//
//@Component
//@AllArgsConstructor
//@NoArgsConstructor
//public class ValidateFields {
//
//    public static BindingResult validateFields(@Valid UserAddDTO user, BindingResult result) {
//
//        if (!user.getPassword().equals(user.getConfirmPassword())) {
//            result.rejectValue("confirmPassword", "error.user", "Password and confirm password are different");
//        }
//
//
//        return result;
//    }
//
//
//}
