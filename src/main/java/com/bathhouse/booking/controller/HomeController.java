package com.bathhouse.booking.controller;

import com.bathhouse.booking.enums.CabynTypes;
import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.*;
import com.bathhouse.booking.service.HomeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private HomeControllerService homeControllerService;

    @Autowired
    public HomeController(HomeControllerService homeControllerService) {
        this.homeControllerService = homeControllerService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Integer> countOfBathhouses = homeControllerService.getListOfCountsOfBathhouses();
        List<Bathhouse> recommendedBathhouses = homeControllerService.getRecommendedBathhouses();

        Schedule schedule = new Schedule();
        List<Boolean> isBooked = List.of(false, true, true, false, false, false,
                true, true, false, false,false, false);
        schedule.setIsBooked(isBooked);

        Schedule schedule1 = new Schedule();
        List<Boolean> isBooked1 = List.of(false, true, false, true, true, false,
                false, true, false, false,true, true);
        schedule1.setIsBooked(isBooked1);

        Cabin cabin = new Cabin();
        cabin.setCapacity(CabynTypes.MEDIUM.toString());
        cabin.setPrice(2000);
        cabin.setSchedule(schedule);
        cabin.setId(14);

        Cabin cabin1 = new Cabin();
        cabin1.setCapacity(CabynTypes.SMALL.toString());
        cabin1.setPrice(1500);
        cabin1.setSchedule(schedule1);
        cabin1.setId(21);

        Set<Cabin> cabins = new HashSet<>();
        cabins.add(cabin);
        cabins.add(cabin1);

        Bathhouse bathhouse = new Bathhouse();
        bathhouse.setImage("/images/bath1.jpg");
        bathhouse.setAddress("698 Candlewood Lane, Cabot Cove, Maine.");
        bathhouse.setCity(Cities.ALMATY.toString());
        bathhouse.setDescription("The history of bath in Japan begins in the 6th Century with the introduction of Buddist purification rituals. The custom was believed to cleanse the body and spirit to promote improved health using heat and steam. This why many temples in Japan have baths.");
        bathhouse.setName("Japanese Bathhouse");
        bathhouse.setPhone_number("+7(707) 458 56 32");
        bathhouse.setRating(4.2);
        bathhouse.setCabins(cabins);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);


        model.addAttribute("countOfBathhouses", countOfBathhouses);
        model.addAttribute("recommendedBathhouses", recommendedBathhouses);
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


    @GetMapping("/login")
    public String login(){

        // If user is authenticated : redirect to 'home' page. Else do log in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegister(Model model){

        model.addAttribute("user", new User());
        // If user is authenticated : redirect to 'home' page. Else do register
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "register";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user){
        if (homeControllerService.findUserByUsername(user.getUsername()) != null)
            return "register";
        homeControllerService.saveUser(user);
        homeControllerService.autoLogin(user.getUsername(), user.getConfirmPassword());
        return "redirect:/";
    }




}
