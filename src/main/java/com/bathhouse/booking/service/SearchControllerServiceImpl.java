package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;
import com.bathhouse.booking.repository.BathhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchControllerServiceImpl implements SearchControllerService{

    private final BathhouseRepository bathhouseRepository;

    @Autowired
    public SearchControllerServiceImpl(BathhouseRepository bathhouseRepository) {
        this.bathhouseRepository = bathhouseRepository;
    }

    @Override
    public List<Bathhouse> findBathByCityTimeCapacity(Optional<String> city, Optional<LocalTime> time, Optional<String> capacity) {

        List<Bathhouse> bathhouses = new ArrayList<>();

        if (city.isPresent() && !city.get().isEmpty())
            bathhouses = bathhouseRepository.findAllByCity(city.get());

        if (capacity.isPresent() && !capacity.get().isEmpty()){
            bathhouses = bathhouses.stream().filter(bath -> {
                bath.getCabins().removeIf(cabin -> !cabin.getCapacity().equals(capacity.get()));
                return !bath.getCabins().isEmpty();
            }).collect(Collectors.toList());
        }

        if (time.isPresent()){
            bathhouses = bathhouses.stream().filter(bath -> {
                bath.getCabins().removeIf(cabin -> cabin.getSchedule().getIsBooked().get(time.get().getHour() - 12));
                return !bath.getCabins().isEmpty();
            }).collect(Collectors.toList());
        }
        return bathhouses;
    }
}
