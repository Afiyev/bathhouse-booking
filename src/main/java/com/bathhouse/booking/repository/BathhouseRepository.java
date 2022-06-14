package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Bathhouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BathhouseRepository extends JpaRepository<Bathhouse, Integer> {
    int countAllByCity(String city);
    List<Bathhouse> findAllByCity(String city);
}
