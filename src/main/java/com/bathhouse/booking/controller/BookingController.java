package com.bathhouse.booking.controller;

import com.bathhouse.booking.model.Reservation;
import com.bathhouse.booking.service.BookingControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class BookingController {

    private final BookingControllerService bookingControllerService;

    @Autowired
    public BookingController(BookingControllerService bookingControllerService) {
        this.bookingControllerService = bookingControllerService;
    }

    @GetMapping("/booking")
    public String booking(@RequestParam(name = "timeIndex") int time,
                          @RequestParam(name = "cabinIndex") int cabinId,
                          Principal principal, Model model){

        Reservation reservation = bookingControllerService.createReservation(principal, cabinId, time);
        model.addAttribute("reservation", reservation);

        return "booking";
    }

    @PostMapping("/booking")
    public String postBooking(@ModelAttribute("reservation") Reservation reservation){
        bookingControllerService.saveReservation(reservation);
        return "redirect:/user-page/my-bookings";
    }
}
