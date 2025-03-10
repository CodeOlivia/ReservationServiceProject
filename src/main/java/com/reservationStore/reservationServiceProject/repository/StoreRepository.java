package com.reservationStore.reservationServiceProject.repository;

import com.reservationStore.reservationServiceProject.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
