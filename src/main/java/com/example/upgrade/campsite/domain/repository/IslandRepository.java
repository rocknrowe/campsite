package com.example.upgrade.campsite.domain.repository;

import com.example.upgrade.campsite.domain.entity.IslandDTO;
import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IslandRepository extends JpaRepository<IslandDTO, Long> {

}
