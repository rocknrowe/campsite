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
@Table(name = "island", schema = "campsite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IslandDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "name", updatable = true, nullable = false)
    private String name;

    @Column(name = "creation_time")
    private OffsetDateTime tmsCreation = OffsetDateTime.now();

    @Column(name = "update_time")
    private OffsetDateTime tmsUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IslandDTO)) return false;
        IslandDTO reservationDTO = (IslandDTO) o;
        return Objects.equals(getName(), reservationDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
