package com.solutions.vasylieva.controller;

import com.solutions.vasylieva.dto.UserAddDTO;
import com.solutions.vasylieva.dto.UserEditDTO;
import com.solutions.vasylieva.exception.NoSuchUserException;
import com.solutions.vasylieva.model.User;
import com.solutions.vasylieva.services.RoleService;
import com.solutions.vasylieva.services.UserService;
import com.solutions.vasylieva.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in DB");
        }
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody @Valid UserAddDTO user, BindingResult result) {

        userService.create(user);
        return UserUtil.toUser(user);
    }

    @PutMapping("/users")
    public User editUser(@RequestBody @Valid UserEditDTO user, Long id) {
        userService.update(UserUtil.toUser(user, id));
        return UserUtil.toUser(user, id);
    }

    @DeleteMapping("/users/{id}")
    public String removeUser(@PathVariable Long id) {

        if (userService.findById(id) == null) {
            throw new NoSuchUserException("Not found user with ID = " + id + " in DB");
        }
            userService.remove(userService.findById(id));
            return "User with ID = " + id + "was deleted";
    }
}
