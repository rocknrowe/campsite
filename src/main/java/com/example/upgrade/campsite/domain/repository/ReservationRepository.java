package com.example.upgrade.campsite.domain.repository;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDTO, Long> {

}
