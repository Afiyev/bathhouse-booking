package com.bathhouse.booking.controller;

import com.bathhouse.booking.enums.CabynTypes;
import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.*;
import com.bathhouse.booking.service.UserPageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        Bathhouse bathhouse = new Bathhouse();
        bathhouse.setAddress("698 Candlewood Lane, Cabot Cove, Maine.");
        bathhouse.setCity(Cities.ALMATY.toString());
        bathhouse.setName("Japanese Bathhouse");
        bathhouse.setPhone_number("+7(707) 458 56 32");

        Cabin cabin = new Cabin();
        cabin.setCapacity(CabynTypes.MEDIUM.toString());
        cabin.setId(14);
        cabin.setPrice(1200);
        cabin.setBathhouse(bathhouse);

        Set<Reservation> reservations = new HashSet<>();
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setId(12);
        reservation.setTime(LocalTime.now());
        reservation.setCabin(cabin);

        Reservation reservation1 = new Reservation();
        reservation1.setUser(user);
        reservation1.setId(12);
        reservation1.setTime(LocalTime.now());
        reservation1.setCabin(cabin);

        Reservation reservation2 = new Reservation();
        reservation2.setUser(user);
        reservation2.setId(12);
        reservation2.setTime(LocalTime.now());
        reservation2.setCabin(cabin);



        reservations.add(reservation);
        reservations.add(reservation1);
        reservations.add(reservation2);

        model.addAttribute("reservations", reservations);
//        model.addAttribute("reservations", user.getReservations());
        return "my-bookings";
    }

    @GetMapping("/user-page/my-bathhouses")
    public String myBathhouses(@RequestParam int cabin_number, Principal principal, Model model){
        User user = userPageControllerService.findUserByUsername(principal.getName());

        List<Bathhouse> bathhouses = new ArrayList<>();

        Cabin cabin = new Cabin();
        cabin.setCapacity(CabynTypes.MEDIUM.toString());
        cabin.setPrice(3000);
        cabin.setId(45);

        Cabin cabin1 = new Cabin();
        cabin1.setCapacity(CabynTypes.SMALL.toString());
        cabin1.setPrice(2500);
        cabin1.setId(123);

        Set<Cabin> cabins = new HashSet<>();
        cabins.add(cabin);
        cabins.add(cabin1);

        Bathhouse bathhouse3 = new Bathhouse();
        bathhouse3.setImage("/images/bath1.jpg");
        bathhouse3.setAddress("698 Candlewood Lane, Cabot Cove, Maine.");
        bathhouse3.setCity(Cities.ALMATY.toString());
        bathhouse3.setDescription("The history of bath in Japan begins in the 6th Century with the introduction of Buddist purification rituals. The custom was believed to cleanse the body and spirit to promote improved health using heat and steam. This why many temples in Japan have baths.");
        bathhouse3.setName("Japanese Bathhouse");
        bathhouse3.setPhone_number("+7(707) 458 56 32");
        bathhouse3.setCabins(cabins);
        bathhouses.add(bathhouse3);
        bathhouses.add(bathhouse3);
        model.addAttribute("bathhouses", bathhouses);
        model.addAttribute("bathhouse", "bathhouse");
        for(int i = 0; i < bathhouses.size(); i++){
            String m = "bathhouse" + i;
            model.addAttribute(m, bathhouses.get(i));
        }
        model.addAttribute("newBathhouse", new Bathhouse());

        CabinCreationDto cabinsForm = new CabinCreationDto();
        for(int i = 0; i < cabin_number; i++){
            cabinsForm.addCabin(new Cabin());
        }
        model.addAttribute("cabins", cabinsForm);

        return "/my-bathhouses";
    }

    @PostMapping("/user-page/update-bathhouse")
    public String updateBathhouse(@ModelAttribute Bathhouse bathhouse){
        userPageControllerService.updateBath(bathhouse);
        return "redirect:/user-page/my-bathhouses?cabin_number=0";
    }
}
