package com.reservationStore.reservationServiceProject.repository;

import com.reservationStore.reservationServiceProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
