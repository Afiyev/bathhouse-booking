package com.bathhouse.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class example {

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }
}
