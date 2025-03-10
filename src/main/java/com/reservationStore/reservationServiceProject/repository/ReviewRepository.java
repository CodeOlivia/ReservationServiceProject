package com.reservationStore.reservationServiceProject.repository;

import com.reservationStore.reservationServiceProject.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
