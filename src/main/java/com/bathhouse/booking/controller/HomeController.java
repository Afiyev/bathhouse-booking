package com.bathhouse.booking.controller;

import com.bathhouse.booking.enums.CabynCapacity;
import com.bathhouse.booking.enums.City;
import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Cabin;
import com.bathhouse.booking.model.Schedule;
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
        List<Bathhouse> recommendedBathhouses = homeControllerService.getRecommendedBathhouses();

        Schedule schedule = new Schedule();
        List<Boolean> hours = List.of(false, true, true, false, false, false,
                true, true, false, false,false, false);
        schedule.setHours(hours);

        Cabin cabin = new Cabin();
        cabin.setCapacity(CabynCapacity.MEDIUM.toString());
        cabin.setPrice(2000);
        cabin.setSchedule(schedule);

        Bathhouse bathhouse = new Bathhouse();
        bathhouse.setImage("/images/bath1.jpg");
        bathhouse.setAddress("Jessica Fletcher (Murder She Wrote) - 698 Candlewood Lane, Cabot Cove, Maine.");
        bathhouse.setCity(City.ALMATY.toString());
        bathhouse.setDescription("The history of bath in Japan begins in the 6th Century with the introduction of Buddist purification rituals. The custom was believed to cleanse the body and spirit to promote improved health using heat and steam. This why many temples in Japan have baths.");
        bathhouse.setName("Japanese Bathhouse");
        bathhouse.setPhone_number("+7(707) 458 56 32");
        bathhouse.setRating(4.2);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);
        recommendedBathhouses.add(bathhouse);


        model.addAttribute("countOfBathhouses", countOfBathhouses);
        model.addAttribute("recommendedBathhouses", recommendedBathhouses);
        return "home";
    }
}
