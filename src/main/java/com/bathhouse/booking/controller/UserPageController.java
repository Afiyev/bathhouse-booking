package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.User;
import com.bathhouse.booking.service.UserPageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserPageController {

    private final UserPageControllerService userPageControllerService;

    @Autowired
    public UserPageController(UserPageControllerService userPageControllerService) {
        this.userPageControllerService = userPageControllerService;
    }

    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal){
        User user = userPageControllerService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-page";
    }
}
