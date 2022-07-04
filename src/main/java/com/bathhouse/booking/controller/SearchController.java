package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.service.SearchControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    private final SearchControllerService service;

    @Autowired
    public SearchController(SearchControllerService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "city") Optional<String> city,
            @RequestParam(value = "time") Optional<LocalTime> time,
            @RequestParam(value = "duration") Optional<Integer> duration,
            @RequestParam(value = "capacity") Optional<String> capacity,
            Model model){
        List<Bathhouse> bathhouses = service.findBathByCityTimeCapacity(city, time, capacity);
        model.addAttribute("recommendedBathhouses", bathhouses);
        return "/search-page";
    }
}
