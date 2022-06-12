package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinRepository extends JpaRepository<Cabin, Integer> {
}
