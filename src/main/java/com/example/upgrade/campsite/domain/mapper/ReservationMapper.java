package com.example.upgrade.campsite.domain.mapper;

import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Mappings({
            @Mapping(target="fullname", source="userName"),
            @Mapping(target="arrivalDate", source="startDate"),
            @Mapping(target="departureDate", source="endDate"),
    })
    Reservation reservationDTOtoReservation(ReservationDTO dto);

    @Mappings({
            @Mapping(target="userName", source="fullname"),
            @Mapping(target="startDate", source="arrivalDate"),
            @Mapping(target="endDate", source="departureDate"),
    })
    ReservationDTO reservationToReservationDTO(Reservation reservation);

    List<Reservation> reservationDTOsToReservations(List<ReservationDTO> reservationDTOS);
}
