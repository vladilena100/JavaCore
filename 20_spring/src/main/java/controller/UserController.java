package controller;

import dto.UserAddDTO;
import dto.UserEditDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.UserService;
import util.UserUtil;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public String users(Model model, @AuthenticationPrincipal User user) {

        if (user.getRole().getName().equals("ADMIN")) {
            List<User> users = userService.findAll();
            model.addAttribute("auth_user", user);
            model.addAttribute("user", users);
            return "adminPage";
        } else {
            List<User> users = userService.findAll();
            model.addAttribute("auth_user", user);
            model.addAttribute("user", users);
            return "userPage";
        }

    }

    @GetMapping("add")
    public String addUserForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("action", "Add");
        model.addAttribute("auth_user", user);
        model.addAttribute("user", new UserAddDTO());
        return "addUpdateUsers";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute(name = "user") @Valid UserAddDTO user, BindingResult result, Model model, @AuthenticationPrincipal User principal) {
        if (!user.getPassword().equals(user.getPasswordAgain())) {
            result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
        }
        model.addAttribute("action", "Add");
        model.addAttribute("auth_user", principal);
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "addUpdateUsers";
        }
        boolean created = userService.create(user);
        if (!created) {
            result.rejectValue("login", "error.user", "User with this login already exists");
            return "addUpdateUsers";
        }
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model, @AuthenticationPrincipal User principal) {

        model.addAttribute("action", "Edit");
        model.addAttribute("id", id);
        model.addAttribute("auth_user", principal);
        User user = userService.findById(id);
        model.addAttribute("user", UserUtil.toUserEdit(user));
        return "redirect:/users";
    }

    @PostMapping("edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute(name = "user") @Valid UserEditDTO user, BindingResult result, Model model, @AuthenticationPrincipal User principal) {
        if (!user.getPassword().equals(user.getPasswordAgain())) {
            result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
        }
        if (!user.getPassword().isEmpty() && user.getPassword().length() < 4 || user.getPassword().length() > 64) {
            result.rejectValue("password", "error.user", "Password length must be from 4 to 64 characters");
        }
        model.addAttribute("action", "Edit");
        model.addAttribute("id", id);
        model.addAttribute("auth_user", principal);
        if (result.hasErrors()) {
            return "addUpdateUsers";
        }
        userService.update(UserUtil.toUser(user, id));
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id, @AuthenticationPrincipal User user) {
        if (!user.getId().equals(id)) {
            userService.remove(user);
        }
        return "redirect:/users";
    }
}
