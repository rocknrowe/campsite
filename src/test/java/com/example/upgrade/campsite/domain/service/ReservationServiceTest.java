package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.mapper.ReservationMapper;
import com.example.upgrade.campsite.domain.repository.ReservationRepository;
import com.example.upgrade.campsite.model.Reservation;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import sun.security.x509.AVA;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ReservationServiceTest {

    private ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private ReservationMapper reservationMapper = Mockito.mock(ReservationMapper.class);
    private AvailabilityService availabilityService = Mockito.mock(AvailabilityService.class);

    private ReservationService reservationService = new ReservationService(reservationRepository, reservationMapper,availabilityService);

    @Nested
    public class TestGetReservations{

        @Test
        @DisplayName("Example test for getting all reservations")
        void sampleTestForMethodA() {

            LocalDate startDate = LocalDate.of(2019,03,10);
            LocalDate endDate = LocalDate.of(2019,03,20);

            long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            List<LocalDate> dates = IntStream.iterate(0, i -> i + 1)
                    .limit(numOfDaysBetween)
                    .mapToObj(i -> startDate.plusDays(i))
                    .collect(Collectors.toList());

            dates.forEach(d->System.out.println(d));
        }

        @Test
        @DisplayName("Example test for getting all reservations")
        void sampleTestForMethodB() {

            List<LocalDate> availableDates = Lists.newArrayList();

            List<ReservationDTO> reservedDays = Lists.newArrayList();
            ReservationDTO reservationDTO1;
            reservationDTO1 = new ReservationDTO();
            reservationDTO1.setId(1l);
            reservationDTO1.setStartDate(LocalDate.of(2020,3,2));
            reservationDTO1.setEndDate(LocalDate.of(2020,3,3));
            reservedDays.add(reservationDTO1);

            ReservationDTO reservationDTO2;
            reservationDTO2 = new ReservationDTO();
            reservationDTO2.setId(2l);
            reservationDTO2.setStartDate(LocalDate.of(2020,3,4));
            reservationDTO2.setEndDate(LocalDate.of(2020,3,6));
            reservedDays.add(reservationDTO2);

            ReservationDTO reservationDTO3;
            reservationDTO3 = new ReservationDTO();
            reservationDTO3.setId(3l);
            reservationDTO3.setStartDate(LocalDate.of(2020,3,8));
            reservationDTO3.setEndDate(LocalDate.of(2020,3,10));
            reservedDays.add(reservationDTO3);

            reservedDays.stream().forEach(r -> {
                long numOfDaysBetween = ChronoUnit.DAYS.between(r.getStartDate(), r.getEndDate().plusDays(1));
                List<LocalDate> datesBetween = IntStream.iterate(0, i -> i + 1)
                        .limit(numOfDaysBetween)
                        .mapToObj(i -> r.getStartDate().plusDays(i))
                        .collect(Collectors.toList());
                availableDates.addAll(datesBetween);
            });

            availableDates.forEach(d->System.out.println(d));

        }

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