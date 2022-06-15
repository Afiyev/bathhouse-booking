package com.bathhouse.booking.controller;

import com.bathhouse.booking.service.HomeControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private HomeControllerService homeControllerService;

    @Autowired
    public HomeController(HomeControllerService homeControllerService) {
        this.homeControllerService = homeControllerService;
    }

    @GetMapping("/")
    public String welcome(Model model){
        List<Integer> countOfBathhouses = homeControllerService.getListOfCountsOfBathhouses();
        model.addAttribute("countOfBathhouses", countOfBathhouses);
        return "home";
    }
}
