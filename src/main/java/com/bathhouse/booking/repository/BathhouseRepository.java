package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Bathhouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BathhouseRepository extends JpaRepository<Bathhouse, Integer> {
}
