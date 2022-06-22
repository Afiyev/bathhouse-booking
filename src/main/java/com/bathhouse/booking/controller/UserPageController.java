package com.bathhouse.booking.controller;

import com.bathhouse.booking.enums.CabynTypes;
import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Cabin;
import com.bathhouse.booking.model.Reservation;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.service.UserPageControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalTime;
import java.util.HashSet;
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
        userPageControllerService.update(user, updatedUser.getUsername(), updatedUser.getMobile_number());
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

}
