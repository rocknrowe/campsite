package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<Reservation> getReservations() {
        List<ReservationDTO> reservationDTOList = reservationRepository.findAll();
        return reservationMapper.reservationDTOsToReservations(reservationDTOList);
    }

    public Reservation saveReservations() {
        ReservationDTO reservation = new ReservationDTO();
        reservation.setUserName("Rowena Dsouza");
        reservation.setEmail("rocknrowe@gmail.com");
        reservation.setStartDate(LocalDate.now());
        reservation.setNumberOfDays(3);
        ReservationDTO savedReservation = reservationRepository.save(reservation);
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }
}
