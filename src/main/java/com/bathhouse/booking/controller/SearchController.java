package com.bathhouse.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalTime;

@Controller
public class SearchController {

    @GetMapping("/bathhouses/cabins/{city}&{capacity}&{time}&{duration}")
    public String filterByCity_Capacity_Time_Duration(
            @PathVariable(name = "city") String city,
            @PathVariable(name = "capacity") String capacity,
            @PathVariable(name = "time") LocalTime time,
            @PathVariable(name = "duration") int duration,
            Model model){

        return "home";
    }
}
