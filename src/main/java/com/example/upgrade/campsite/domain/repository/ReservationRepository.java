package com.example.upgrade.campsite.domain.repository;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDTO, Long> {

    @Query("SELECT r FROM ReservationDTO r where (r.startDate >= :startDate AND r.startDate <= :endDate) " +
            "OR (r.endDate >= :startDate AND r.endDate <= :endDate) " +
            "OR (:startDate = :endDate AND r.startDate <= :startDate AND r.endDate >= :startDate)")
    Optional<List<ReservationDTO>> findEntriesGreaterEqualStartDateAndLessEqualEndDate(@Param("startDate") LocalDate startDate,
                                                                                              @Param("endDate") LocalDate endDate);
}
