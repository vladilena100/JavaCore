package com.solutions.vasylieva.controller;

import com.solutions.vasylieva.dto.UserAddDTO;
import com.solutions.vasylieva.dto.UserEditDTO;
import com.solutions.vasylieva.exception.NoSuchUserException;
import com.solutions.vasylieva.model.User;
import com.solutions.vasylieva.services.UserService;
import com.solutions.vasylieva.util.UserUtil;
import com.solutions.vasylieva.util.ValidateFields;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateFields validateFields;

    //    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/users")
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

    @GetMapping("/userByEmail/{email}")
    public User getUserBuEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new NoSuchUserException("There is no user with email = " + email + " in DB");
        }
        return user;
    }

    @GetMapping("/userByLogin/{login}")
    public User getUserBuLogin(@PathVariable String login) {
        User user = userService.findByLogin(login);
        if (user == null) {
            throw new NoSuchUserException("There is no user with login = " + login + " in DB");
        }
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody @Valid UserAddDTO user, BindingResult result) {

        validateFields.validateFields(user, result);
        if (!result.hasErrors()) {
            userService.create(user);
            User userForAdd = UserUtil.toUser(user);
            return userService.findByLogin(userForAdd.getLogin());
        } else {
            throw new NoSuchUserException("Input data is not correct");
        }
    }

    @PutMapping("/users")
    public User editUser(@RequestBody @Valid UserEditDTO userDTO, BindingResult result) {

        validateFields.validateFields(userDTO, result);
        if (!result.hasErrors()) {
            User user = UserUtil.toUser(userDTO, userService.findByLogin(userDTO.getLogin()).getId());
            userService.update(user);
            return userService.findByLogin(userDTO.getLogin());
        } else {
            throw new NoSuchUserException("Input data is not correct");
        }

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
