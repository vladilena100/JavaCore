package controller;

import dto.CaptchaResponseDTO;
import dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import services.UserService;
import util.UserUtil;

import java.net.URI;

@Controller
@RequestMapping("registration")
@PropertySource("classpath:application.properties")
public class RegistrationController {

    private final UserService userService;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @Value("${recaptcha.url}")
    private String verificationUrl;

    private final RestTemplate restTemplate;

    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "registration";
    }

    @PostMapping
    public String registration(
            @ModelAttribute(name = "user")
            @Valid UserRegisterDTO user,
            BindingResult result, Model model,
            @RequestParam("g-recaptcha-response") String captchaResponse
    ) {
        CaptchaResponseDTO response = captchaVerificationRequest(captchaResponse);
        if (response == null || !response.isSuccess()) {
            model.addAttribute("captchaError", "Captcha is required");
        }
        if (!user.getPassword().equals(user.getPasswordAgain())) {
            result.rejectValue("passwordAgain", "error.user", "Password and confirm password are different");
        }
        model.addAttribute("user", user);
        if (result.hasErrors() || !response.isSuccess()) {
            return "registration";
        }
        boolean registered = userService.registerUser(UserUtil.toUser(user));
        if (!registered) {
            result.rejectValue("login", "error.user", "User with this login already exists");
            return "registration";
        }
        return "redirect:/login";
    }

    private CaptchaResponseDTO captchaVerificationRequest(String captchaResponse) {
        URI uri = UriComponentsBuilder.fromHttpUrl(verificationUrl)
                .queryParam("secret", recaptchaSecret)
                .queryParam("response", captchaResponse).build().toUri();
        return restTemplate.postForObject(uri, null, CaptchaResponseDTO.class);
    }
}
