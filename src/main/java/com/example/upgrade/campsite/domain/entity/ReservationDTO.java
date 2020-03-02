package com.example.upgrade.campsite.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reservation", schema = "campsite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "user_name", updatable = false, nullable = false)
    private String userName;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Column(name = "start_date", updatable = false, nullable = false)
    private LocalDate startDate;

    @Column(name = "number_of_days", updatable = false, nullable = false)
    private Integer numberOfDays;

    @Column(name = "creation_time")
    private OffsetDateTime tmsCreation;

    @Column(name = "update_time")
    private OffsetDateTime tmsUpdate;
}
