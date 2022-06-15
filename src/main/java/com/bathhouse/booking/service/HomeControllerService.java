package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;

import java.util.List;

public interface HomeControllerService {
    List<Bathhouse> findAllBathhousesByCity(String city);
    List<Bathhouse> getRecommendedBathhouses();
    List<Integer> getListOfCountsOfBathhouses();
}
