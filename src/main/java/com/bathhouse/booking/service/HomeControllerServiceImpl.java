package com.bathhouse.booking.service;

import com.bathhouse.booking.enums.Cities;
import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.model.Recommendation;
import com.bathhouse.booking.model.Role;
import com.bathhouse.booking.model.User;
import com.bathhouse.booking.repository.BathhouseRepository;
import com.bathhouse.booking.repository.RecommendationRepository;
import com.bathhouse.booking.repository.RoleRepository;
import com.bathhouse.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeControllerServiceImpl implements HomeControllerService{

    private final BathhouseRepository bathhouseRepository;
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public HomeControllerServiceImpl(BathhouseRepository bathhouseRepository, RecommendationRepository recommendationRepository,
                                     UserRepository userRepository, RoleRepository roleRepository,
                                     BCryptPasswordEncoder passwordEncoder) {
        this.bathhouseRepository = bathhouseRepository;
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public void saveUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
