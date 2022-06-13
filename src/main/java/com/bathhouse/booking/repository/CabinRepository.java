package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinRepository extends JpaRepository<Cabin, Integer> {
    List<Cabin> findCabinsByBathhouse_CityAndCapacity(String city, String capacity);
}
