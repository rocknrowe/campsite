package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.exceptions.DatesUnavailableException;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type Reservation service.
 */
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private AvailabilityService availabilityService;

    private int sum;

    /**
     * Instantiates a new Reservation service.
     *
     * @param reservationRepository the reservation repository
     * @param reservationMapper     the reservation mapper
     * @param availabilityService   the availability service
     */
    @Autowired
    public ReservationService(final ReservationRepository reservationRepository,
                              final ReservationMapper reservationMapper,
                              final AvailabilityService availabilityService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.availabilityService = availabilityService;
    }

    /**
     * Gets reservations.
     *
     * @param id the id
     * @return the reservations
     */
    public Reservation getReservations(String id) {
        Optional<ReservationDTO> reservationDTO = reservationRepository.findById(Long.valueOf(id));
        if(reservationDTO.isPresent()){
            return reservationMapper.reservationDTOtoReservation(reservationDTO.get());
        } else {
            return null; //TODO: throw exception
        }
    }

    /**
     * Gets reservations.
     *
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        List<ReservationDTO> reservationDTOList = reservationRepository.findAll();
        return reservationMapper.reservationDTOsToReservations(reservationDTOList);
    }

    /**
     * Save reservations reservation.
     *
     * @param reservation the reservation
     * @return the reservation
     */
    public Reservation saveReservations(Reservation reservation) {
        ReservationDTO savedReservation = saveReservation(reservationMapper.reservationToReservationDTO(reservation));
        return reservationMapper.reservationDTOtoReservation(savedReservation);
    }

    /**
     * Delete reservations boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteReservations(String id) {
        reservationRepository.deleteById(Long.valueOf(id));
        return true;
    }

    /**
     * Update reservations reservation.
     *
     * @param reservation the reservation
     * @return the reservation
     */
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

    /**
     * Save reservation reservation dto.
     *
     * @param reservationToSave the reservation to save
     * @return the reservation dto
     */
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

    /**
     * Get over lapping reservations list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    public List<Reservation> getOverLappingReservations(final LocalDate startDate, final LocalDate endDate){
        Optional<List<ReservationDTO>> existingReservationDuringSelectionDays = reservationRepository.findEntriesGreaterEqualStartDateAndLessEqualEndDate(startDate, endDate);
        if(existingReservationDuringSelectionDays.isPresent()){
            return reservationMapper.reservationDTOsToReservations(existingReservationDuringSelectionDays.get());
        }
        return Collections.emptyList();
    }
}
