package com.example.upgrade.campsite.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservation", schema = "campsite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "user_name", updatable = true, nullable = false)
    private String userName;

    @Email
    @Column(name = "email", updatable = true, nullable = false)
    private String email;

    @Column(name = "start_date", updatable = true, nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", updatable = true, nullable = false)
    private LocalDate endDate;

    @Min(1)
    @Max(3)
    @Column(name = "number_of_days", updatable = true, nullable = false)
    private Integer numberOfDays;

    @Column(name = "creation_time")
    private OffsetDateTime tmsCreation = OffsetDateTime.now();

    @Column(name = "update_time")
    private OffsetDateTime tmsUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationDTO)) return false;
        ReservationDTO reservationDTO = (ReservationDTO) o;
        return Objects.equals(getUserName(), reservationDTO.getUserName()) &&
                Objects.equals(getEmail(), reservationDTO.getEmail()) &&
                Objects.equals(getStartDate(), reservationDTO.getStartDate()) &&
                Objects.equals(getEndDate(), reservationDTO.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
