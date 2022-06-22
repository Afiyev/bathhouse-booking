package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Role;
import com.bathhouse.booking.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface HomeControllerService {
    List<Bathhouse> findAllBathhousesByCity(String city);
    List<Bathhouse> getRecommendedBathhouses();
    List<Integer> getListOfCountsOfBathhouses();
}
