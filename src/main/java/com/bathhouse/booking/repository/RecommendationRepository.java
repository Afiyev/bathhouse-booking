package com.bathhouse.booking.repository;

import com.bathhouse.booking.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
