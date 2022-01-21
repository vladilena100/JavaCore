package controller;

import dto.UserDTO;
import lombok.AllArgsConstructor;
import model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;

@Controller
@RequestMapping("login")
@AllArgsConstructor
public class LoginController {

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    @GetMapping
    public String login(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/users";
        }
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute(name = "userDTO") UserDTO userDTO) {

        User user = (User) userDetailsService.loadUserByUsername(userDTO.getLogin());
        if (userService.checkCorrectData(user, userDTO)) {
            return "redirect:/users";
        }
        return "redirect:/login?error=true";
    }
}
