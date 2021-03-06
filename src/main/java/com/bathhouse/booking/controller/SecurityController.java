package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.User;
import com.bathhouse.booking.service.SecurityControllerService;
import com.bathhouse.booking.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {

    private final SecurityControllerService securityControllerService;
    private final UserValidator userValidator;

    @Autowired
    public SecurityController(SecurityControllerService securityControllerService, UserValidator userValidator) {
        this.securityControllerService = securityControllerService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String login() {

        // If user is authenticated : redirect to 'home' page. Else do log in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/user-page";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        model.addAttribute("user", new User());
        // If user is authenticated : redirect to 'home' page. Else do register
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register";
        }
        return "redirect:/user-page";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request, BindingResult bindingResult, Model model) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        } else {

            securityControllerService.saveUser(user);

            return "redirect:/login";
        }
    }
}
