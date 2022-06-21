package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Role;
import com.bathhouse.booking.model.User;

import java.util.List;

public interface HomeControllerService {
    List<Bathhouse> findAllBathhousesByCity(String city);
    List<Bathhouse> getRecommendedBathhouses();
    List<Integer> getListOfCountsOfBathhouses();
    void saveUser(User user);
    void autoLogin(String username, String password);
    User findUserByUsername(String username);
}
