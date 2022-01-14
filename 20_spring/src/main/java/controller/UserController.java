package controller;

import dao.DaoUser;
import dto.UserAddDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private final UserService userService;

    @RequestMapping("/users")
    public String allUsers(Model model) {

        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);

        return "adminPage.jsp";
    }

    @RequestMapping("/add")
    public String addUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "addUsers.jsp";
    }

    @RequestMapping("/addUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @RequestMapping("/edit")
    public String updateUser(@RequestParam("id") Long id, Model model) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "addUsers.jsp";
    }



//    @RequestMapping("/edit")
//    public String updateUser(@ModelAttribute("users") User user) {
//
//        userService.update(user);
//
//        return "redirect:/users";
//    }

//    @GetMapping
//    public String users(Model model, @AuthenticationPrincipal User user) {
//        List<User> users = userService.findAll();
//        model.addAttribute("auth_user", user);
//        model.addAttribute("users", users);
//        return "users";
//    }
//
//    @GetMapping("add")
//    public String addUserForm(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("action", "Add");
//        model.addAttribute("auth_user", user);
//        model.addAttribute("user", new UserAddDTO());
//        return "addUser";
//    }

//    @PostMapping("add")
//    public String addUser(@ModelAttribute(name = "user") @Valid UserAddDTO user, BindingResult result, Model model, @AuthenticationPrincipal User principal) {
//        if (!user.getPassword().equals(user.getConfirmPassword())) {
//            result.rejectValue("confirmPassword", "error.user", "Password and confirm password are different");
//        }
//        model.addAttribute("action", "Add");
//        model.addAttribute("auth_user", principal);
//        model.addAttribute("user", user);
//        if (result.hasErrors()) {
//            return "addUser";
//        }
//        boolean created = userService.create(user);
//        if (!created) {
//            result.rejectValue("login", "error.user", "User with this login already exists");
//            return "addUser";
//        }
//        return "redirect:/users";
//    }

}
