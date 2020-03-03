package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityServiceTest {

    private ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private ReservationMapper reservationMapper = Mockito.mock(ReservationMapper.class);

    private AvailabilityService availabilityService = new AvailabilityService(reservationRepository, reservationMapper);

    @Nested
    class TestGetAvailabilities {

        List<ReservationDTO> reservationDTOList = Lists.newArrayList();

        @BeforeEach
        public void init(){

            List<ReservationDTO> reservedDays = com.google.common.collect.Lists.newArrayList();
            ReservationDTO reservationDTO1;
            reservationDTO1 = new ReservationDTO();
            reservationDTO1.setId(1l);
            reservationDTO1.setStartDate(LocalDate.of(2020,3,2));
            reservationDTO1.setEndDate(LocalDate.of(2020,3,3));
            reservationDTOList.add(reservationDTO1);

            ReservationDTO reservationDTO2;
            reservationDTO2 = new ReservationDTO();
            reservationDTO2.setId(2l);
            reservationDTO2.setStartDate(LocalDate.of(2020,3,4));
            reservationDTO2.setEndDate(LocalDate.of(2020,3,6));
            reservationDTOList.add(reservationDTO2);

            ReservationDTO reservationDTO3;
            reservationDTO3 = new ReservationDTO();
            reservationDTO3.setId(3l);
            reservationDTO3.setStartDate(LocalDate.of(2020,3,8));
            reservationDTO3.setEndDate(LocalDate.of(2020,3,10));
            reservationDTOList.add(reservationDTO3);

        }

        @Test
        void getAvailabilities() {

            LocalDate startDate = LocalDate.of(2020, 03, 01);
            LocalDate endDate = LocalDate.of(2020, 03, 10);

            Mockito.when(reservationRepository.findEntriesGreaterEqualStartDateAndLessEqualEndDate(startDate, endDate))
                    .thenReturn(Optional.of(reservationDTOList));
            List<LocalDate> availableDates = availabilityService.getAvailabilities(startDate, endDate);

            Assertions.assertEquals(2, availableDates.size());

        }
    }
}