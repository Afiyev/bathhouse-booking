package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.*;
import com.bathhouse.booking.service.HomeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

        model.addAttribute("countOfBathhouses", countOfBathhouses);
        model.addAttribute("recommendedBathhouses", recommendedBathhouses);
        return "home";
    }

}
