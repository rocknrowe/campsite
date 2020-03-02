package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Reservation getReservations(String id) {
        Optional<ReservationDTO> reservationDTO = reservationRepository.findById(Long.valueOf(id));
        if(reservationDTO.isPresent()){
            return reservationMapper.reservationDTOtoReservation(reservationDTO.get());
        } else {
            return null; //TODO: throw exception
        }
    }

    public List<Reservation> getReservations() {
        List<ReservationDTO> reservationDTOList = reservationRepository.findAll();
        return reservationMapper.reservationDTOsToReservations(reservationDTOList);
    }

    public Reservation saveReservations(Reservation reservation) {
        ReservationDTO savedReservation = reservationRepository.save(reservationMapper.reservationToReservationDTO(reservation));
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }

    public boolean deleteReservations(String id) {
        reservationRepository.deleteById(Long.valueOf(id));
        return true;
    }

    public Reservation updateReservations(String id, Reservation reservation) {
        ReservationDTO savedReservation = reservationRepository.save(reservationMapper.reservationToReservationDTO(reservation));
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }
}
