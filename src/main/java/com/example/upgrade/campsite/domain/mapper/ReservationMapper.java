package com.example.upgrade.campsite.domain.mapper;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * The interface Reservation mapper.
 */
@Mapper
public interface ReservationMapper {

    /**
     * Reservation dt oto reservation reservation.
     *
     * @param dto the dto
     * @return the reservation
     */
    @Mappings({
            @Mapping(target="fullname", source="userName"),
            @Mapping(target="arrivalDate", source="startDate"),
            @Mapping(target="departureDate", source="endDate"),
    })
    Reservation reservationDTOtoReservation(ReservationDTO dto);

    /**
     * Reservation to reservation dto reservation dto.
     *
     * @param reservation the reservation
     * @return the reservation dto
     */
    @Mappings({
            @Mapping(target="userName", source="fullname"),
            @Mapping(target="startDate", source="arrivalDate"),
            @Mapping(target="endDate", source="departureDate"),
    })
    ReservationDTO reservationToReservationDTO(Reservation reservation);

    /**
     * Reservation dt os to reservations list.
     *
     * @param reservationDTOS the reservation dtos
     * @return the list
     */
    List<Reservation> reservationDTOsToReservations(List<ReservationDTO> reservationDTOS);
}
