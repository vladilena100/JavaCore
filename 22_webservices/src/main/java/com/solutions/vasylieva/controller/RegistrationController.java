//package com.solutions.vasylieva.controller;
//
//import com.solutions.vasylieva.dto.CaptchaResponseDTO;
//import com.solutions.vasylieva.dto.UserRegisterDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.com.solutions.vasylieva.util.UriComponentsBuilder;
//import com.solutions.vasylieva.services.UserService;
//import com.solutions.vasylieva.util.UserUtil;
//import com.solutions.vasylieva.util.ValidateFields;
//
//import javax.validation.Valid;
//import java.net.URI;
//
//@Controller
//@RequestMapping("registration")
//@PropertySource("classpath:application.properties")
//public class RegistrationController {
//
//    private final UserService userService;
//
//    @Autowired
//    private ValidateFields validateFields;
//
//    @Value("${recaptcha.secret}")
//    private String recaptchaSecret;
//
//    @Value("${recaptcha.url}")
//    private String verificationUrl;
//
//    private final RestTemplate restTemplate;
//
//    public RegistrationController(UserService userService, RestTemplate restTemplate) {
//        this.userService = userService;
//        this.restTemplate = restTemplate;
//    }
//
//    @GetMapping
//    public String registration(Model model) {
//        model.addAttribute("user", new UserRegisterDTO());
//        return "registration";
//    }
//
//    @PostMapping
//    public String registration(@Valid @ModelAttribute(name = "user") UserRegisterDTO user, BindingResult result, Model model,
//            @RequestParam("g-recaptcha-response") String captchaResponse) {
//
//        CaptchaResponseDTO response = captchaVerificationRequest(captchaResponse);
//        if (response == null || !response.isSuccess()) {
//            model.addAttribute("captchaError", "Captcha is required");
//        }
//
//        model.addAttribute("user", user);
//        validateFields.validateFields(user, result);
//        if (result.hasErrors() || !response.isSuccess()) {
//            model.addAttribute("user", user);
//            return "registration";
//        }
//        userService.registerUser(UserUtil.toUser(user));
//
//        return "redirect:/login";
//    }
//
//    private CaptchaResponseDTO captchaVerificationRequest(String captchaResponse) {
//        URI uri = UriComponentsBuilder.fromHttpUrl(verificationUrl)
//                .queryParam("secret", recaptchaSecret)
//                .queryParam("response", captchaResponse).build().toUri();
//        return restTemplate.postForObject(uri, null, CaptchaResponseDTO.class);
//    }
//}
