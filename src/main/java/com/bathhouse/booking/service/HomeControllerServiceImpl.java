package com.bathhouse.booking.service;

import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Recommendation;
import com.bathhouse.booking.repository.BathhouseRepository;
import com.bathhouse.booking.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeControllerServiceImpl implements HomeControllerService{

    private final BathhouseRepository bathhouseRepository;
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public HomeControllerServiceImpl(BathhouseRepository bathhouseRepository, RecommendationRepository recommendationRepository) {
        this.bathhouseRepository = bathhouseRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Bathhouse> findAllBathhousesByCity(String city) {
        return bathhouseRepository.findAllByCity(city);
    }

    @Override
    public List<Integer> getListOfCountsOfBathhouses() {
        List<Integer> countsOfBathhouses = new ArrayList<>();
        for (Cities city : Cities.values()){
            countsOfBathhouses.add(bathhouseRepository.countAllByCity(city.toString()));
        }
        return countsOfBathhouses;
    }

    @Override
    public List<Bathhouse> getRecommendedBathhouses() {
        List<Recommendation> recommendations = recommendationRepository.findAll();
        List<Bathhouse> recommendedBathhouses = new ArrayList<>();
        recommendedBathhouses = recommendations.stream()
                .map(recommendation -> recommendation.getBathhouse())
                .collect(Collectors.toList());
        return recommendedBathhouses;
    }
}
