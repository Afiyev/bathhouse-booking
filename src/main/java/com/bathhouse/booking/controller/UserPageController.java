package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.*;
import com.bathhouse.booking.service.UserPageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/user-page/personal-details")
    public String personalDetails(Principal principal, Model model){
        User user = userPageControllerService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "personal-details";
    }

    @PostMapping("/user-page/personal-details")
    public String postPersonalDetails(@ModelAttribute User updatedUser, Principal principal){
        User user = userPageControllerService.findUserByUsername(principal.getName());
        userPageControllerService.updateUser(user, updatedUser.getUsername(), updatedUser.getMobile_number());
        return "redirect:/logout";
    }

    @GetMapping("/user-page/my-bookings")
    public String myBookings(Principal principal, Model model){
        User user = userPageControllerService.findUserByUsername(principal.getName());
        model.addAttribute("reservations", user.getReservations());
        return "my-bookings";
    }

    @GetMapping("/user-page/my-bathhouses")
    public String myBathhouses(@RequestParam int cabin_number, Principal principal, Model model){
        User user = userPageControllerService.findUserByUsername(principal.getName());
        userPageControllerService.addModelAttributes(user, model, cabin_number);

        return "/my-bathhouses";
    }

    @PostMapping("/user-page/update-bathhouse")
    public String updateBathhouse(@RequestParam("updatedimage")MultipartFile image,
                                  @ModelAttribute("") Bathhouse bathhouse){
        userPageControllerService.updateBath(bathhouse,image);
        return "redirect:/user-page/my-bathhouses?cabin_number=0";
    }

    @PostMapping("/user-page/add-bathhouse")
    public String addBathhouse(@ModelAttribute("cabins") CabinCreationDto cabins,
                               @RequestParam("newimage")MultipartFile image,
                               Principal principal){
        userPageControllerService.addBathhouse(cabins, image, principal);
        return "redirect:/user-page/my-bathhouses?cabin_number=0";
    }
}
