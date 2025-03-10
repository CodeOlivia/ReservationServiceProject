package com.reservationStore.reservationServiceProject.repository;

import com.reservationStore.reservationServiceProject.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, String> {

    Optional<Partner> findByPartnerId(String partnerId);;
}
