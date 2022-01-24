//package com.solutions.vasylieva.controller;
//
//import com.solutions.vasylieva.dto.UserDTO;
//import com.solutions.vasylieva.model.User;
//import com.solutions.vasylieva.services.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/login")
//@AllArgsConstructor
//public class LoginController {
//
//    private final UserDetailsService userDetailsService;
//
//    private final UserService userService;
////
////    @GetMapping
////    public String login(Model model, @AuthenticationPrincipal User user) {
////        if (user != null) {
////            return "redirect:/users";
////        }
////        model.addAttribute("userDTO", new UserDTO());
////        return "login";
////    }
//
//    @PostMapping
//    public User login(@RequestBody UserDTO userDTO) {
//
//        User user = (User) userDetailsService.loadUserByUsername(userDTO.getLogin());
//        if (userService.checkCorrectData(user, userDTO)) {
//
//            return user;
//        }
//        return null;
//    }
//
//}
