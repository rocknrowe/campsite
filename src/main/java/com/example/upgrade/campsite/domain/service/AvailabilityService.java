package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The type Availability service.
 */
@Service
public class AvailabilityService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;


    /**
     * Instantiates a new Availability service.
     *
     * @param reservationRepository the reservation repository
     * @param reservationMapper     the reservation mapper
     */
    @Autowired
    public AvailabilityService(final ReservationRepository reservationRepository,
                               final ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Is available boolean.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the boolean
     */
    public boolean isAvailable(final LocalDate startDate, final LocalDate endDate){

        List<LocalDate> availableDates = getAvailabilities(startDate, endDate);
        List<LocalDate> localDates = getDatesBetweenTwoDates(startDate, endDate);

        boolean allDatesavailable = localDates.stream().allMatch( d -> availableDates.contains(d));
        return allDatesavailable;
    }

    /**
     * This function is used to get the availabilities
     *
     * @param startDate start date for the search
     * @param endDate   end date for the search
     * @return list
     */
    public List<LocalDate> getAvailabilities(final LocalDate startDate, final LocalDate endDate){

        List<LocalDate> listOfDates = getDatesBetweenTwoDates(startDate, endDate);
        List<LocalDate> existingReservationDates = getExistingReservationDates(startDate, endDate);

        if(existingReservationDates!= null && existingReservationDates.size()!=0){
            listOfDates.removeAll(existingReservationDates);
        }
        return listOfDates;
    }

    private List<LocalDate> getExistingReservationDates(final LocalDate startDate, final LocalDate endDate) {

        List<LocalDate> availableDates = Lists.newArrayList();

        Optional<List<ReservationDTO>> existingReservationDuringSelectionDays = reservationRepository.findEntriesGreaterEqualStartDateAndLessEqualEndDate(startDate, endDate);
        if(existingReservationDuringSelectionDays.isPresent()){
            List<ReservationDTO> reservedDays = existingReservationDuringSelectionDays.get();

            reservedDays.stream().forEach(r -> {
                long numOfDaysBetween = ChronoUnit.DAYS.between(r.getStartDate(), r.getEndDate().plusDays(1));
                List<LocalDate> datesBetween = IntStream.iterate(0, i -> i + 1)
                        .limit(numOfDaysBetween)
                        .mapToObj(i -> r.getStartDate().plusDays(i))
                        .collect(Collectors.toList());
                availableDates.addAll(datesBetween);
            });
        }

        return availableDates;
    }

    private List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
