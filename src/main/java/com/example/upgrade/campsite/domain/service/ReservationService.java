package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.exceptions.DatesUnavailableException;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AvailabilityService availabilityService;

    private int sum;

    @Autowired
    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationMapper reservationMapper,
                              final AvailabilityService availabilityService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.availabilityService = availabilityService;
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
        ReservationDTO savedReservation = saveReservation(reservationMapper.reservationToReservationDTO(reservation));
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }

    public boolean deleteReservations(String id) {
        reservationRepository.deleteById(Long.valueOf(id));
        return true;
    }

    public Reservation updateReservations(Reservation reservation) {
        ReservationDTO savedReservation = saveReservation(reservationMapper.reservationToReservationDTO(reservation));
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }

//    protected ReservationDTO validateAndSaveReservation(final Reservation reservation){
//        boolean validateDates = true;
//
//        //if existing reservation
//        if(reservation.getId()!= null){
//            Optional<ReservationDTO> reservationDTO = reservationRepository.findById(reservation.getId());
//            if(reservationDTO.isPresent()){
//                ReservationDTO originalReservation = reservationDTO.get();
//                if (originalReservation.equals(reservationMapper.reservationToReservationDTO(reservation))){
//                    validateDates = false;
//                }
//            }
//        }
//        return saveReservation(reservationMapper.reservationToReservationDTO(reservation));
//    }

    protected ReservationDTO saveReservation(final ReservationDTO reservationToSave){

        synchronized (this) {
            ReservationDTO savedReservation = null;
            System.out.println("In synchronized block");
            System.out.println(reservationToSave.getId());
            if (availabilityService.isAvailable(reservationToSave.getStartDate(), reservationToSave.getEndDate())){
                savedReservation = reservationRepository.save(reservationToSave);
            } else {
                throw new DatesUnavailableException("Reservation dates not available");
            }
            return savedReservation;
        }
    }

    public List<Reservation> getOverLappingReservations(final LocalDate startDate, final LocalDate endDate){
        Optional<List<ReservationDTO>> existingReservationDuringSelectionDays = reservationRepository.findEntriesGreaterEqualStartDateAndLessEqualEndDate(startDate, endDate);
        if(existingReservationDuringSelectionDays.isPresent()){
            return reservationMapper.reservationDTOsToReservations(existingReservationDuringSelectionDays.get());
        }
        return Collections.emptyList();
    }
}
