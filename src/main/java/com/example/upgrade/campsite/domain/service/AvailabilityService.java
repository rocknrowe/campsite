package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.google.common.collect.Lists;
import org.codehaus.plexus.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AvailabilityService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;


    @Autowired
    public AvailabilityService(final ReservationRepository reservationRepository,
                               final ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
    }

    public boolean isAvailable(final LocalDate startDate, final LocalDate endDate){
        //Optional<List<ReservationDTO>> existingReservationDuringSelectionDays = reservationRepository.findEntriesGreaterEqualStartDateAndLessEqualEndDate(startDate, endDate);
        return true;
    }

    //This function is used to get the availabilities
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
