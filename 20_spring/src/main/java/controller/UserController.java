package controller;

import dto.UserAddDTO;
import dto.UserEditDTO;
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
import services.RoleService;
import services.UserService;
import util.UserUtil;
import util.ValidateFields;

import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/users")

@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ValidateFields validateFields;

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
        model.addAttribute("roles", roleService.findAll());
        return "addUpdateUsers";
    }

    @PostMapping("add")
    public String addUser(@Valid @ModelAttribute(name = "userAddDTO") UserAddDTO user, BindingResult result, Model model, @AuthenticationPrincipal User principal, HttpServletRequest req) {
//        if (!user.getPassword().equals(user.getPasswordAgain())) {
//            result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
//        }

//        if (!user.getPassword().equals(user.getPasswordAgain())) {
//            result.rejectValue("confirmPassword", "error.user", "Password and confirm password are different");
//        }
//        model.addAttribute("action", "Add");
//        model.addAttribute("auth_user", principal);
//        model.addAttribute("user", user);
//        if (result.hasErrors()) {
//            return "addUpdateUsers";
//        }
//        boolean created = userService.create(user);
//        if (!created) {
//            result.rejectValue("login", "error.user", "User with this login already exists");
//            return "addUpdateUsers";
//        }
//        return "redirect:/users";

        model.addAttribute("action", "Add");
        model.addAttribute("auth_user", principal);
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.findAll());
            return "addUpdateUsers";
        }

        if (validateFields.validateFields(user, result, req).hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.findAll());
            return "addUpdateUsers";
        }
        userService.create(user);

        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model, @AuthenticationPrincipal User principal) {


            model.addAttribute("action", "Edit");
            model.addAttribute("id", id);
            model.addAttribute("auth_user", principal);
            User user = userService.findById(id);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("selectedRoleId", user.getRole().getId());
            model.addAttribute("birthday", formatter.format(user.getBirthday()));
            model.addAttribute("user", UserUtil.toUserEdit(user));
            model.addAttribute("roles", roleService.findAll());
            return "addUpdateUsers";
    }

    @PostMapping("edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute(name = "user") @Valid UserEditDTO user, BindingResult result, Model model, @AuthenticationPrincipal User principal) {

        model.addAttribute("action", "Edit");
        model.addAttribute("id", id);
        model.addAttribute("auth_user", principal);
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.findAll());
            return "addUpdateUsers";
        }
        userService.update(UserUtil.toUser(user, id));
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id, @AuthenticationPrincipal User user) {
        if (!user.getId().equals(id)) {
            userService.remove(userService.findById(id));
        }
        return "redirect:/users";
    }
}
