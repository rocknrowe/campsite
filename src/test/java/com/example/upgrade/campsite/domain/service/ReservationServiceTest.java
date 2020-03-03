package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ReservationServiceTest {

    private ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private ReservationMapper reservationMapper = Mockito.mock(ReservationMapper.class);
    private AvailabilityService availabilityService = Mockito.mock(AvailabilityService.class);

    private ReservationService reservationService = new ReservationService(reservationRepository, reservationMapper,availabilityService);

    @Nested
    public class TestGetReservations{

    }

    @Nested
    public class TestSaveReservations{

        private ReservationDTO reservationDTO1;
        private ReservationDTO reservationDTO2;
        private ReservationDTO reservationDTO3;

        @BeforeEach
        public void init(){

            reservationDTO1 = new ReservationDTO();
            reservationDTO1.setId(1l);

            reservationDTO2 = new ReservationDTO();
            reservationDTO2.setId(2l);

            reservationDTO3 = new ReservationDTO();
            reservationDTO3.setId(3l);

            ReservationDTO reservationDTORet1 = new ReservationDTO();
            reservationDTORet1.setId(1l);
            reservationDTORet1.setUserName("Rowena 1");

            ReservationDTO reservationDTORet2 = new ReservationDTO();
            reservationDTORet1.setId(2l);
            reservationDTORet1.setUserName("Rowena 2");

            ReservationDTO reservationDTORet3 = new ReservationDTO();
            reservationDTORet1.setId(3l);
            reservationDTORet1.setUserName("Rowena 3");

            Mockito.when(reservationRepository.save(Mockito.refEq(reservationDTO1))).thenReturn(reservationDTORet1);
            Mockito.when(reservationRepository.save(Mockito.refEq(reservationDTO1))).thenReturn(reservationDTORet2);
            Mockito.when(reservationRepository.save(Mockito.refEq(reservationDTO1))).thenReturn(reservationDTORet3);
        }

        @Test
        public void givenMultiThread_whenMethodSync() throws InterruptedException {
            ExecutorService service = Executors.newFixedThreadPool(3);


            Callable callableTask1 = () -> {
                    return reservationService.saveReservation(reservationDTO1);
            };

            Callable callableTask2 = () -> {
                return reservationService.saveReservation(reservationDTO2);
            };

            Callable callableTask3 = () -> {
                return reservationService.saveReservation(reservationDTO3);
            };
            System.out.println(service.submit(callableTask2));
            System.out.println(service.submit(callableTask1));
            System.out.println(service.submit(callableTask3));
            System.out.println(service.submit(callableTask2));
            System.out.println(service.submit(callableTask3));
            service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        }
    }

}