package com.bathhouse.booking.service;

import com.bathhouse.booking.model.Bathhouse;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SearchControllerService {
    List<Bathhouse> findBathByCityTimeCapacity(Optional<String> city, Optional<LocalTime> time, Optional<String> capacity);
}
